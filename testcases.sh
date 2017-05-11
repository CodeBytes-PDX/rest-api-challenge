HOST_IP='127.0.0.1:5000'
echo
echo "Starting curl requests"
echo

# Hello World
URL=$HOST_IP
URL+='/' #paste your relative path here
echo "Sending request to $URL"
curl $URL
echo "Expected: Hello World"
echo

# Get a list of library books
# ex[app]/books
URL=$HOST_IP
URL+='/books' #paste your relative path here
echo "Sending request to $URL"
curl $URL
echo "Expected: Not Implemented"
echo

# Get a specific library book
# [app]/book/5
URL=$HOST_IP
URL+='/book/5' #paste your relative path here
echo "Sending request to $URL"
curl $URL
echo "Expected: Book 5"
echo

# Find out if a library book is available
# GET [app]/book/5/status
URL='/' #paste your relative path here
echo "Expected: Not Implemented"
echo

# Add a book to the library
# POST [app]/book/add
URL='/' #paste your relative path here
echo "Expected: Not Implemented"
echo

# Check out a book to a user
# GET [app]/user/5/checkout/5
URL='/' #paste your relative path here
echo "Expected: Not Implemented"
echo

# Return a book
# GET [app]/book/5/return
URL='/' #paste your relative path here
echo "Expected: Not Implemented"
echo

# list users
# GET [app]/users
URL='/' #paste your relative path here
echo "Expected: Not Implemented"
echo

# list library books checked out to a specific user
# GET [app]/user/5/books
URL='/' #paste your relative path here
echo "Expected: Not Implemented"
echo
