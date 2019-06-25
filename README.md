# waiter-service

## Run the sample
First, build it:
```shell
mvn package
```
or build it without testing:
```shell
mvn package -Dmaven.test.skip
```

Then, run it:
```shell
java -jar target/waiter-service-xxx.jar
```

## Run with docker (development)
First, build docker image:
```shell
docker build . -t helanzhu/waiter-service-h2:0.0.1-SNAPSHOT
```

Then, run docker image:
```
docker run --name waiter-service-h2 -d -p 8080:8080 -p 8081:8081 helanzhu/waiter-service-h2:0.0.1-SNAPSHOT
```

## Swagger
1. We use springfox (https://github.com/springfox/springfox), to automatically scan REST API endpoints and expose online swagger spec.

   * Swagger spec: http://localhost:8080/v2/api-docs
   
       The release swagger spec is downloaded here and saved in src/main/resources/swagger_v1.0.json
   
   * Swagger UI: http://localhost:8080/swagger-ui.html

## Actuator: Health, Info, Metrics, and so on

   * Health: http://localhost:8081/actuator/health
   * Metrics for Prometheus: http://localhost:8081/actuator/prometheus
   * Spring Components: http://localhost:8081/actuator/beans
   * Spring Configurations: http://localhost:8081/actuator/configprops
   * Environment: http://localhost:8081/actuator/env
   * Info: http://localhost:8081/actuator/info
   * Loggers: http://localhost:8081/actuator/loggers
   * Heapdump: http://localhost:8081/actuator/heapdump
   * Threaddump: http://localhost:8081/actuator/threaddump
   * HTTP endpoints: http://localhost:8081/actuator/mappings
   * ......