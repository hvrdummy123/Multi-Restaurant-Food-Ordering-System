FROM openjdk:23-jdk-slim
WORKDIR /app
COPY target/eureka-server-0.0.1-SNAPSHOT.jar /app/demo.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/app/demo.jar"]