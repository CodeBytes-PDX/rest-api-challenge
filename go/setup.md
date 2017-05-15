# Go Setup

You will need Go version 1.7 or higher installed.

To check the version:

```
$ go version
```

On a mac install using homebrew:

```
$ brew install golang
```

On linux use the package manager appropriate for the OS (`apt-get`, `yum`, etc.):

```
$ sudo apt-get install golang-1.8-go
```

On windows follow the official [installation instructions][install-win].


## Confirm $GOPATH

Go installs third party packages and dependencies to the directory specified by the `$GOPATH` environment variable.

As of Go 1.8 a value of `$HOME/go` is used as the default if not explicity set.

Confirm a value is set for $GOPATH:

```
$ go env | grep GOPATH || echo 'GOPATH is not set!'
```

## Install helper libraries

This project mostly makes use of the Go standard library but also includes a couple of external packages that help with URL parsing and application logging.

Install `gorilla` web mux (router) and `negroni` middleware (logging, panic recovery):

```
$ go get -u github.com/gorilla/mux

$ go get -u github.com/urfave/negroni
```

## Build and Run

Compile and run the app using `go run`:

```
$ go run app.go
```

Alternatively you can compile to a binary and run:

```
$ go build -o codebytes  *.go  && ./codebytes -h
```



[install-win]: https://golang.org/doc/install#windows


