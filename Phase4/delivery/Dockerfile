FROM openjdk:23-jdk-slim
WORKDIR /app
COPY target/delivery-0.0.1-SNAPSHOT.jar /app/demo.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/app/demo.jar"]