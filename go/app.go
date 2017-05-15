package main

import (
	"encoding/json"
	"flag"
	"fmt"
	"log"
	"net/http"
	"os"
	"time"

	"github.com/gorilla/mux"
	"github.com/urfave/negroni"
)

// See this online tool for generating Go structs based on JSON
//  https://mholt.github.io/json-to-go/

// book struct based on JSON in data file
// note the fields are Capitalcase (exported) for proper JSON handling
type book struct {
	ID              int    `json:"id"`
	AuthorFirstName string `json:"author_first_name"`
	AuthorLastName  string `json:"author_last_name"`
	Title           string `json:"title"`
	Pages           int    `json:"pages"`
}

// user struct based on JSON in data file
// note the fields are Capitalcase (exported) for proper JSON handling
type user struct {
	ID        int    `json:"id"`
	FirstName string `json:"first_name"`
	LastName  string `json:"last_name"`
	Email     string `json:"email"`
	Gender    string `json:"gender"`
	IPAddress string `json:"ip_address"`
}

type payload struct {
	Message string      `json:"message,omitempty"`
	Data    interface{} `json:"data,omitempty"`
}

var books []book
var users []user

func main() {
	var booksFile string
	var peopleFile string
	var port int

	flag.StringVar(&booksFile, "b", "data/books.json", "books data file")
	flag.StringVar(&peopleFile, "p", "data/people.json", "people data file")
	flag.IntVar(&port, "port", 8080, "Server listen port")
	flag.Parse()

	loadFromFile(booksFile, &books)
	loadFromFile(peopleFile, &users)
	fmt.Printf("%d entries loaded from %q\n", len(books), booksFile)
	fmt.Printf("%d entries loaded from %q\n", len(peopleFile), booksFile)

	routes := mux.NewRouter()
	routes.HandleFunc("/", helloHandler)

	routes.HandleFunc("/books", getAllBooks).Methods("GET")
	routes.HandleFunc("/books", createBook).Methods("POST")
	routes.HandleFunc("/books/{id:[0-9]+}", getBook).Methods("GET")

	routes.HandleFunc("/users", getAllUsers).Methods("GET")
	routes.HandleFunc("/users", createUser).Methods("POST")
	routes.HandleFunc("/users/{id:[0-9]+}", getUser).Methods("GET")

	// routes.HandleFunc("/boom", func(w http.ResponseWriter, req *http.Request) { panic("boom") })

	logging := negroni.NewLogger()
	logging.SetFormat("{{.StartTime}} | {{.Status}} | {{.Hostname}} | {{.Method}} {{.Path}} \n")
	middleware := negroni.New(
		negroni.NewRecovery(),
		logging,
		negroni.NewStatic(http.Dir("data")),
	)
	middleware.UseHandler(routes)

	fmt.Printf("Listening on http://localhost:%d/\n\n", port)
	listen := fmt.Sprintf(":%d", port)
	http.ListenAndServe(listen, middleware)
}

func helloHandler(resp http.ResponseWriter, req *http.Request) {
	jsonify(resp, &payload{Message: "Hello World!"})
}

func getAllBooks(resp http.ResponseWriter, req *http.Request) {
	jsonify(resp, &payload{"book total", len(books)})
}

func getBook(resp http.ResponseWriter, req *http.Request) {
	vars := mux.Vars(req)
	id := vars["id"]

	randomIdx := time.Now().Nanosecond() % len(books)

	jsonify(resp, &payload{"Random book instead of Book " + id, books[randomIdx]})
}

func createBook(resp http.ResponseWriter, req *http.Request) {
	var bk book
	err := fromJSON(req, &bk)
	if err != nil {
		msg := fmt.Sprintf("Error decoding JSON: %s", err)
		jsonify(resp, msg)
		return
	}

	result := payload{"Received book", bk}
	jsonify(resp, &result)
}

func getAllUsers(resp http.ResponseWriter, req *http.Request) {
	jsonify(resp, &payload{Message: "** TODO **"})
}

func getUser(resp http.ResponseWriter, req *http.Request) {
	vars := mux.Vars(req)
	id := vars["id"]

	jsonify(resp, &payload{Message: "Looking for User " + id})
}

func createUser(resp http.ResponseWriter, req *http.Request) {
	var u user
	err := fromJSON(req, &u)
	if err != nil {
		msg := fmt.Sprintf("Error decoding JSON: %s", err)
		jsonify(resp, msg)
		return
	}

	result := payload{"Received User", u}
	jsonify(resp, &result)
}

// write response as JSON
func jsonify(resp http.ResponseWriter, data interface{}) {
	resp.Header().Set("Content-type", "application/json")

	// first convert to a JSON string
	// respond with a server error if any problems encountered
	dataJSON, err := json.Marshal(data)
	if err != nil {
		serverError(resp, "Error encoding JSON response: "+err.Error())
		return
	}

	resp.Write(dataJSON)
}

func badRequest(resp http.ResponseWriter, details string) {
	msg := payload{Message: "Bad Request", Data: details}
	resp.WriteHeader(http.StatusBadRequest)
	jsonify(resp, msg)
}

func serverError(resp http.ResponseWriter, details string) {
	msg := payload{Message: "Internal server error", Data: details}
	resp.WriteHeader(http.StatusInternalServerError)
	jsonify(resp, msg)
}

// populates object using JSON from request body
func fromJSON(req *http.Request, object interface{}) error {
	return json.NewDecoder(req.Body).Decode(object)
}

// popuates container object using JSON loaded from specified file
func loadFromFile(path string, container interface{}) {
	// open data file and complain if we can't find or read it
	file, err := os.Open(path)
	if err != nil {
		log.Fatalf("Unable to load file %q - %s", path, err)
	}
	// close the file once done here
	defer file.Close()

	// read the contents, parsing the JSON
	err = json.NewDecoder(file).Decode(container)
	if err != nil {
		log.Fatalf("Error decoding JSON from file %q - %s", path, err)
	}
}
