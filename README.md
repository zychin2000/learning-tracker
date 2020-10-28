# DatabaseProject

How to run the server:
1. Download and install [Maven](https://maven.apache.org/install.html). Maven is a Java package processing tool, which automatically manages and downloads packages.
2. Run the command `mvn verify` at the project's root directory. It downloads all the required packages for the project. It only needs to be performed once, or when a new package is added.
3. To start the server, run `mvn tomcat7:run` at the project's root directory. A webserver will be automatically launched, and you can access the webapp at http://localhost:8080/