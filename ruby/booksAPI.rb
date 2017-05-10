require 'sinatra/base'
require 'JSON'

class Booklistapp < Sinatra::Base

#----- Home endpoint

	get '/' do 
		"Books RubyAPI"
	end

#----- Books API endpoints

	books = File.read('books.json')
	books_db =JSON.parse(books)

	get '/books' do
		books_db.to_s
	end

#----- People API endpoints

	people = File.read('people.json')
	people_db = JSON.parse(people)

	get '/people' do
		people_db.to_s
	end	

	run! if app_file == $0
	
end



