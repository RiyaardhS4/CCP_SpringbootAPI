FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ./target/demo2-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]