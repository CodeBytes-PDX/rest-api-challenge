package codebytes;

import codebytes.ResponsePayload.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static spark.Spark.afterAfter;
import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.internalServerError;
import static spark.Spark.port;
import static spark.Spark.post;

public class App {
    private static Logger LOG = LoggerFactory.getLogger(App.class);

    private static final int SERVER_PORT = 8000;

    private static final Path BOOKS_FILE = Paths.get("data/books.json");
    private static final Path PEOPLE_FILE = Paths.get("data/people.json");

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_JSON = "application/json";

    private JsonTransformer transformer;


    public App(JsonTransformer transformer) {
        this.transformer = transformer;
    }

    public void runServer() {
        LOG.info("Current working directory: '{}'", System.getProperty("user.dir"));
        List<User> users = loadData(PEOPLE_FILE, User.class);
        List<Book> books = loadData(BOOKS_FILE, Book.class);

        port(SERVER_PORT);
        LOG.info("Listening on http://localhost:{}", SERVER_PORT);

        // root path: greeting
        get("/", (req, res) -> {
            res.header(CONTENT_TYPE, "text/html");
            return "<h1>Hello World!</h1>";
        });

        // users
        get("/users", (req, res) -> {
            return new ResponsePayload(Status.SUCCESS,
                    "Total Users: " + users.size(),
                    "** TO DO **");
        }, transformer);

        Random random = new Random();
        get("/users/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            int randomIdx = random.nextInt(users.size());
            String cheekyMsg = String.format("Requested User(%d) however returning random response", id);

            return new ResponsePayload(Status.SUCCESS,
                                   cheekyMsg,
                                   users.get(randomIdx));
        }, transformer);

        post("/users", (req, res) -> {
            User user = transformer.fromJSON(req.body(), User.class);
            LOG.info("User data posted: {}", user);

            // do something with user

            return new ResponsePayload(Status.SUCCESS,
                                   "Processed new user",
                                   user);
        }, transformer);

        // books
        get("/books", (req, res) -> "** TO DO **", transformer);
        get("/books/:id", (req, res) -> "Book " + req.params(":id"), transformer);
        post("/books", (req, res) -> "** TO DO **", transformer);

        //
        // additional handling
        //
        // set default content type to JSON
        before((req, resp) -> resp.header(CONTENT_TYPE, CONTENT_JSON));

        // log after each request
        afterAfter((req, resp) -> {
            LOG.info("{} '{} {}' {}", req.ip(), req.requestMethod(), req.pathInfo(), resp.status());
        });

        // respond to errors with HTML
        internalServerError((req, res) -> {
            res.header(CONTENT_TYPE, "text/html");
            return "<h1>500 - Internal Server Error</h1>";
        });

    }


    // load JSON file and parse as list of specified type
    private  <T> List<T> loadData(Path path, Class<T> cls) {
        LOG.info("  Loading data file '{}' found: {}", path, path.toFile().exists());

        try (Reader reader = Files.newBufferedReader(path)) {
            return transformer.listFromJSON(reader, cls);
        } catch (IOException ioe) {
            LOG.error("Error processing file {}", path, ioe);
            return Collections.emptyList();
        }
    }

    // main entry point
    public static void main(String[] args) {
        JsonTransformer transformer = new JsonTransformer();
        App app = new App(transformer);
        app.runServer();
    }

}
