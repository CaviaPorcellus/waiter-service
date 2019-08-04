FROM java:8
LABEL maintainer="kevin.jin01@sap.com"

WORKDIR /waiter-service
EXPOSE 8080

ARG JAR_FILE
ADD target/${JAR_FILE} ./waiter-service.jar
ENTRYPOINT ["java", "-jar","waiter-service.jar"]