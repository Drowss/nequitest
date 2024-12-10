FROM openjdk:17-slim
COPY target/nequitest-0.0.1-SNAPSHOT.jar nequi-app.jar
ENTRYPOINT ["java", "-jar", "nequi-app.jar"]