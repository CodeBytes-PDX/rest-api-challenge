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
		book.to_s
	  
	end

	#CREATE a book
	# post '/books/add' do
	# end

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

	#GET a specific person
	get '/people/:id' do |id|
		person = people_db.select {|people_db| people_db["id"] == id.to_i}
		person.to_s
	  
	end

	#CREATE a person
	# post '/people/add' do
	# end

	# UPDATE a person
	# patch '/people/:id' do 
	# end

	run! if app_file == $0
	
end



