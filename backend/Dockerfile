FROM openjdk:17-alpine
CMD ["mkdir", "app"]
WORKDIR app/
COPY target/*.jar app/rickmorty.jar
EXPOSE 8082
CMD ["java", "-jar", "app/rickmorty.jar"]