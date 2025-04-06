FROM openjdk:23-jdk-slim
WORKDIR /app
COPY target/restaurantService-0.0.1-SNAPSHOT.jar /app/demo.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/demo.jar"]