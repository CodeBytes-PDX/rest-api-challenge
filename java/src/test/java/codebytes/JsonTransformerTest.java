package codebytes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class JsonTransformerTest {

    // update this if the JSON schema (format) changes in people.json
    private String EXPECTED_USER_JSON_FORMAT = "{\n" +
            "  \"id\": 1,\n" +
            "  \"first_name\": \"Christophe\",\n" +
            "  \"last_name\": \"Burbage\",\n" +
            "  \"email\": \"cburbage0@yolasite.com\",\n" +
            "  \"gender\": \"Male\",\n" +
            "  \"ip_address\": \"157.168.44.141\"\n" +
            "}";

    private User expectedUser = new User(1, "Christophe", "Burbage", "cburbage0@yolasite.com", "Male", "157.168.44.141");


    // update this if the JSON schema (format) changes in books.json
    private static String EXPECTED_BOOK_JSON_FORMAT = "{\n" +
            "  \"id\": 1,\n" +
            "  \"author_first_name\": \"Kacie\",\n" +
            "  \"author_last_name\": \"Ledgeway\",\n" +
            "  \"title\": \"Monitored empowering encoding\",\n" +
            "  \"pages\": 324\n" +
            "}";

    private Book expectedBook = new Book(1, "Kacie", "Ledgeway", "Monitored empowering encoding", 324);


    private JsonTransformer jsonTransformer;
    private ObjectMapper mapper;

    private List<User> users;
    private List<Book> books;

    @Before
    public void setUp() {
        jsonTransformer = new JsonTransformer();
        mapper = new ObjectMapper();

        User user1 = new User(100, "first 1", "last 1", "u1@example.com", "F", "192.168.1.1");
        User user2 = new User(200, "first 2", "last 2", "u2@example.com", "M", "10.0.0.1");
        users = Arrays.asList(user1, user2);

        Book book1 = new Book(300, "author first 1", "author last 1", "A Great Title", 111);
        Book book2 = new Book(400, "author first 2", "author last 2", "A Wonderful Title", 222);
        books = Arrays.asList(book1, book2);
    }


    @Test
    public void roundTripSingle() throws Exception {
        String userJson = mapper.writeValueAsString(users.get(0));
        User userFromJson = jsonTransformer.fromJSON(userJson, User.class);
        assertEquals("Expected User to equal original after JSON round trip", users.get(0), userFromJson);

        String bookJson = mapper.writeValueAsString(books.get(0));
        Book bookFromJson = jsonTransformer.fromJSON(bookJson, Book.class);
        assertEquals("Expected Book to equal original after JSON round trip", books.get(0), bookFromJson);
    }

    @Test
    public void roundTripList() throws Exception {
        String userListJson = mapper.writeValueAsString(users);
        List<User> usersFromJson = jsonTransformer.listFromJSON(userListJson, User.class);
        assertEquals("Expected User list to equal original after JSON round trip", users, usersFromJson);

        String bookListJson = mapper.writeValueAsString(books);
        List<Book> booksFromJson = jsonTransformer.listFromJSON(bookListJson, Book.class);
        assertEquals("Expected Book list to equal original after JSON round trip", books, booksFromJson);
    }

    @Test
    public void confirmUserModel() {
        User user = jsonTransformer.fromJSON(EXPECTED_USER_JSON_FORMAT, User.class);
        assertEquals("Unexpected User data", expectedUser, user);
    }

    @Test
    public void confirmBookModel() {
        Book book = jsonTransformer.fromJSON(EXPECTED_BOOK_JSON_FORMAT, Book.class);
        assertEquals("Unexpected Book data", expectedBook, book);
    }

}
