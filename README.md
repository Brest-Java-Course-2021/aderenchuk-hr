[![Java CI with Maven](https://github.com/Brest-Java-Course-2021/afedasiuk-hr/actions/workflows/maven.yml/badge.svg)](https://github.com/Brest-Java-Course-2021/afedasiuk-hr/actions/workflows/maven.yml)

# Human resources test project

![Java CI with Maven](https://github.com/Brest-Java-Course-2021/afedasiuk/workflows/Java%20CI%20with%20Maven/badge.svg)

# Brest Java Course 2021 (winter)

This is sample 'Human Resources' web application.

## Requirements

* JDK 11
* Apache Maven

## Build application:
```
mvn clean install
```

## Local tests with Jetty Maven Plugin

The [Jetty Maven plugin](https://www.eclipse.org/jetty/documentation/jetty-10/programming-guide/index.html#jetty-maven-plugin) is useful for development and nd local testing.

From the same directory as your root pom.xml, type:
```
mvn jetty:run
```

This starts Jetty and serves up your project on [http://localhost:8080](http://localhost:8080).
