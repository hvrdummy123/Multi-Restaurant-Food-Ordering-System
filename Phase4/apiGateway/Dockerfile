FROM openjdk:23-jdk-slim
WORKDIR /app
COPY target/apiGateway-0.0.1-SNAPSHOT.jar /app/demo.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/app/demo.jar"]