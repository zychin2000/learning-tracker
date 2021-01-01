FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package


# Install Tomcat    & openjdk 8 (openjdk has java and javac)
FROM tomcat:jdk8-openjdk
# Copy source files to tomcat folder structure
COPY --from=build /home/app/target/learning-principle-0.1.war /usr/local/tomcat/webapps/

# Serve Tomcat
EXPOSE 8080

CMD ["catalina.sh", "run"]