package codebytes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

public class App {

    // server port
    private static final int PORT = 8000;

    public static void main(String[] args) {
        Logger LOG = LoggerFactory.getLogger(App.class);

        port(PORT);
        LOG.info("Listening on http://localhost:{}", PORT);

        // root path: greeting
        get("/", (req, res) -> "Hello Java Programmer!");

        // users
        get("/users", (req, res) -> "TO DO");
        get("/users/:id", (req, res) -> "User " + req.params(":id"));
        post("/users", (req, res) -> "TO DO");

        // books
        get("/books", (req, res) -> "TO DO");
        get("/books/:id", (req, res) -> "Book " + req.params(":id"));
        post("/books", (req, res) -> "TO DO");
    }
}
