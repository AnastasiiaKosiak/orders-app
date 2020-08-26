FROM openjdk:11-oracle
ADD target/order-app-0.0.1-SNAPSHOT.jar order-app.jar
ENTRYPOINT ["java", "-jar", "order-app.jar"]