FROM openjdk:23-jdk-slim
WORKDIR /app
COPY target/config-server-0.0.1-SNAPSHOT.jar /app/demo.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "/app/demo.jar"]