# Postgres Coingecko

Fetch from https://api.coingecko.com/api/v3/coins/list and persist in Postgres SQL

### Prerequisites
- JDK v21.0.2
- Maven v3.9.6

### Build
- setjdk21
- ./mvnw clean package
- ./mvnw clean test

### Run
- colima start --cpu 4 --memory 8
- cd ~/dev/repository/git/docker27/postgres/src/v1
- docker-compose up
- http://localhost:5050/
- admin123four
- ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
- ./mvnw spring-boot:run -Dspring-boot.run.profiles=docker
- ./mvnw spring-boot:run -Dspring-boot.run.profiles=prod

### Api documentation
- http://localhost:8081/postgres-coingecko-coins-list/swagger-ui/index.html
- http://localhost:8081/postgres-coingecko-coins-list/v3/api-docs

### Actuator
- http://localhost:8081/postgres-coingecko-coins-list/actuator
- http://localhost:8081/postgres-coingecko-coins-list/actuator/health
- http://localhost:8081/postgres-coingecko-coins-list/actuator/beans
- http://localhost:8081/postgres-coingecko-coins-list/actuator/env
- http://localhost:8081/postgres-coingecko-coins-list/actuator/logfile
- http://localhost:8081/postgres-coingecko-coins-list/actuator/loggers
- http://localhost:8081/postgres-coingecko-coins-list/actuator/metrics
- http://localhost:8081/postgres-coingecko-coins-list/actuator/threaddump

### Play
- curl -I http://localhost:8081/postgres-coingecko-coins-list/v1/version
- curl -I http://localhost:8081/postgres-coingecko-coins-list/v1/coinslist
