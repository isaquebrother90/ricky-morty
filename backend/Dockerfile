FROM openjdk:17-alpine
ARG JARFILE=target/*.jar
COPY ${JARFILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]