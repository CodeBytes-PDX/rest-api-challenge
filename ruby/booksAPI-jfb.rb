#!/usr/bin/env ruby

# Jeff Blank's work:
# - Added /books/title/search_string for searching books by title (case-insensitive)
# - Added /books/search/search_string for searching books by title or author (case-insensitive)
# - Added /people/search/search_string for searching people by name or e-mail address (case-insensitive)

require 'sinatra'
require 'sinatra/base'
require 'JSON'

class Booklistapp < Sinatra::Base

#----- Home endpoint

  get '/' do
    "Books RubyAPI"
  end

#----- Books API endpoints

  books = File.read('books.json')
  books_db = JSON.parse(books)
  
  # List of all books
  get '/books' do
    books_db.to_s
  end

  # GET a specific book
  get '/books/:id' do |id|
    book = books_db.select {|books_db| books_db["id"] == id.to_i}
    book.size > 0 ? book.to_s : "Not found"
  end

  # find books by searching titles and authors
  get '/books/search/:query' do |q|
    book = books_db.select { |books_db|
      books_db["title"].downcase.index(q.downcase) or
      books_db["author_first_name"].downcase.index(q) or
      books_db["author_last_name"].downcase.index(q)
    }
    book.size > 0 ? book.to_s : "Not found"
  end

  get '/books/title/:title' do |title|
    book = books_db.select {|books_db| books_db["title"].downcase.index(title.downcase)}
    book ? book.to_s : "Not found"
  end

  # CREATE a book
  post '/books/add' do
  end

  # UPDATE a book
  # patch '/books/:id' do
  # end


#----- People API endpoints

  people = File.read('people.json')
  people_db = JSON.parse(people)
  
  # List all people
  get '/people' do
    people_db.to_s
  end        

  # GET a specific person
  get '/people/:id' do |id|
    person = people_db.select {|people_db| people_db["id"] == id.to_i}
    person.to_s
  end

  # CREATE a person
  # post '/people/add' do
  # end

  # UPDATE a person
  # patch '/people/:id' do
  # end

  # search people by name or e-mail address
  get '/people/search/:name' do |name|
    people = people_db.select { |people_db|
      people_db["first_name"].downcase.index(name.downcase) or
      people_db["last_name"].downcase.index(name.downcase) or
      people_db["email"].downcase.index(name.downcase)
    }
    people.size > 0 ? people.to_s : "Not found"
  end

  run! if app_file == $0
  
end
