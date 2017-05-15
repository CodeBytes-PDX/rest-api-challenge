# Java Setup

You will need a java 1.8 JDK (java development kit) installed.

To check the java version:

```
$ java -version
```

Download and install the JDK for your operating system if needed: 
[download site][jdk]


## Build and Run

This project is organized using a standard java Gradle/Maven directory structure and it uses the [Gradle][gradle] build tool for compiling the code.

The first time gradle executes it will download the required libraries for the project.
Subsequent runs use a local disk cache of downloaded files and execute much faster.

Execute `gradlew` with the _clean_ and _build_ tasks:

```
$ ./gradlew clean build
```


Use the `run` task to launch the application:

```
$ ./gradlew run
```

Visit the [root url][app] to see the hello world end point.

Use `Control-C` to stop the application.


Start exploring the code with file: `src/main/java/codebytes/App.java`


[jdk]: http://www.oracle.com/technetwork/java/javase/downloads/index.html
[gradle]: https://gradle.org/docs
[app]: http://localhost:8000/
