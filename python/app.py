import json
from flask import Flask
from flask import jsonify, request

app = Flask(__name__)


# Load the 'databases'
with open('data/books.json') as json_data:
    book_db = json.load(json_data)

with open('data/people.json') as json_data:
    user_db = json.load(json_data)


### General Routes
@app.route("/")
def hello():
    resp = "Hello World!"
    return jsonify(resp)


### User Routes
@app.route("/users", methods=['GET'])
def get_users():
    num_users = len(user_db)
    resp = "There are {} users".format(num_users)
    return jsonify(resp)

@app.route("/user/<int:user_id>", methods=['GET'])
def get_user(user_id):
    resp = "User {}".format(user_id)
    return jsonify(resp)

@app.route("/user/add", methods=['POST'])
def new_user():
    resp = "Unable to add user"
    return jsonify(resp)


### Book Routes
@app.route("/books", methods=['GET'])
def get_books():
    resp = "Not implemented"
    return jsonify(resp)

@app.route("/book/<int:user_id>", methods=['GET'])
def get_book(book_id):
    resp = "Book " + user_id
    return jsonify(resp)

@app.route("/book/add", methods=['POST'])
def new_book():
    new_id = len(book_db) + 1
    data = request.json

    try:
        new_book = {
            "id": new_id,
            "author_first_name": data['author_first_name'],
            "author_last_name": data['author_last_name'],
            "title": data['title'],
            "pages": data['pages']}
        book_db.append(new_book)
    except KeyError:
        resp = "Book not added. Incorrect data format"
        return jsonify(resp)

    resp = "Book added"
    return jsonify(resp)

if __name__ == "__main__":
    app.run()