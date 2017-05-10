what you will need to run a sinatra app:

Install ruby version 2.3.1 `brew install ruby 2.3.1`

Install sinatra `gem install sinatra`

Install bundler `gem install bundler`

*not mandatory* Install rbenv `brew install rbenv`

---

Once you have everything downloaded:

make sure the ruby version you are currently using is 2.3.1: `rbenv local`

if it is wrong `rbenv local 2.3.1`.

Next we need to make sure all dependent gems are installed

use `bundle install` while in the "ruby" directory

You should be able to run the app at this point `ruby booksAPI.rb`

Once the server is runing you will see:
`== Sinatra (v2.0.0) has taken the stage on 4567 for development with backup from WEBrick
[2017-05-09 21:42:56] INFO  WEBrick::HTTPServer#start: pid=6023 port=4567`

you can access your site at `http://localhost:{port#}`




