// server.js
// where the node app starts

// init project
import express from 'express'
import fs from 'fs'
const app = express()


const books = JSON.parse(fs.readFileSync('data/books.json'))
const people = JSON.parse(fs.readFileSync('data/people.json'))

//send html file with example api requests
app.get("/", (req, res) => {
  res.sendFile(__dirname + '/index.html')
})

//send people.json
app.get("/people", (req, res) => {
  res.status(200).send(people)
})

//send books.json or search if query provided
app.get("/books", (req, res) => {
  if(req.query.search){
    res.status(200).send(books.filter((elm, i, arr) => {
      return elm.title.match(req.query.search)
    }))
  }
  else{
    res.status(200).send(books)
  }
})

//send book with id
app.get("/book/:id", (req, res) => {
  res.status(200).send(books[req.params.id-1])
})

//check something with book with id
app.get("/book/:id/:action", (req, res) => {
  switch(req.params.action){
    case 'status': res.status(200).send(books[req.params.id-1].available ? "available" : "checked out")
    break
    default: res.status(404).send('404: Could not find action "' + req.params.action + '"')
  }
})

// listen for requests :)
const listener = app.listen(process.env.PORT || 8000, function () {
  console.log('Your app is listening on port ' + listener.address().port)
})
