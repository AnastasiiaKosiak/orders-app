FROM adoptopenjdk/openjdk11:jdk11u-alpine-nightly-slim
ADD target/order-app-0.0.1-SNAPSHOT.jar order-app.jar
ENTRYPOINT ["java", "-jar", "order-app.jar"]
