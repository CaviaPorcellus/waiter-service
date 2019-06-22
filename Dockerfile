FROM java:8
LABEL maintainer="kevin.jin01@sap.com"

WORKDIR /waiter-service
EXPOSE 8080
EXPOSE 8081

ADD target/waiter-service-0.0.1-SNAPSHOT.jar ./waiter-service.jar
ENTRYPOINT ["java", "-jar","waiter-service.jar"]