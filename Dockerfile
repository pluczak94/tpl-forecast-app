FROM gcr.io/distroless/java:11
COPY target/weather-app.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]