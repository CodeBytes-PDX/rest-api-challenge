If you already have a python3 environment set up, just use that. 

You can check whether you have python install with 

```
$ python -v
$ python3 -v
```

If you have a Python 2 version and a Python 3 version you will most likely see different versions for those two commands. We'll be using Python 3, although the sample app may work with Python 2 as well.

If you don't have version 3.6 or higher installed...

on a mac: 

```
brew install python3
```

or on linux:

```
apt-get install python3
```

or on windows is a little more involved:
https://docs.python.org/3/using/windows.html
http://www.aaronstannard.com/how-to-setup-a-proper-python-environment-on-windows/


Then set up a virtual environment to keep packages separate from any other python projects you may work on 

In the `rest-api-challenge/python/` directory:

```
$ python3 -m venv env
$ source env/bin/activate
(env) $ pip install -r requirements.txt
```

You will be able to do this challenge with just those libraries and the ones that are part of Python's standard library, but you can install more libraries in this environment with:

```
$ pip install [library name]
```

now, you can run the application with 

```
$ python app.py
```

Alternatively, you can run the application in _debug_ mode which reloads the app when changes are saved.  This is done by setting the environment variable `FLASK_DEBUG`:

```
$ FLASK_DEBUG=1 python app.py
```


visit the root url to see the hello world endpoint