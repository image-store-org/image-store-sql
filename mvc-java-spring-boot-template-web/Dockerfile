#Dockerfile
# Start with a base image containing Java runtime

FROM adoptopenjdk:11-jre-hotspot-focal

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG WAR_FILE=target/*.war

# Add the application's jar to the container
COPY ${WAR_FILE} app.war

# Run the jar file
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/app.war"]