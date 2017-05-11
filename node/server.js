// server.js
// where your node app starts

// init project
var express = require('express');
var app = express();
var fs = require('fs');

var books = JSON.parse(fs.readFileSync('data/books.json'));
var people = JSON.parse(fs.readFileSync('data/people.json'));

// we've started you off with Express,
// but feel free to use whatever libs or frameworks you'd like through `package.json`.

// http://expressjs.com/en/starter/static-files.html
app.use(express.static('public'));

// http://expressjs.com/en/starter/basic-routing.html
app.get("/", function (request, response) {
  response.sendFile(__dirname + '/views/index.html');
});

app.get("/people", function (request, response) {
  response.set('Content-Type', 'application/json');
  response.status(200).send(people[0]);
});

// listen for requests :)
var listener = app.listen(process.env.PORT, function () {
  console.log('Your app is listening on port ' + listener.address().port);
});
