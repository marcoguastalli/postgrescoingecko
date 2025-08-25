# Docker
Build and deploy the app using Docker instructions

### build image from Dockerfile
- `setjdk21`
- `./mvnw clean package`
- `docker build --no-cache -t postgrescoingecko:v0.0.2 . -f Dockerfile_api`

### run image created from Dockerfile
- `docker run --rm --name postgrescoingecko -p 8080:8081 --network=v1_postgres-pgadmin postgrescoingecko:v0.0.2`

### access the image as root
- `docker run -it postgrescoingecko:v0.0.2 /bin/sh`
- `cat /etc/os-release`

### access the container as root
- `docker container ls docker exec -u root -it CONTAINER_ID /bin/sh`

### logs from the container
- `tail -n 1000 -f /logs/ postgrescoingecko.log`

### logs
- `docker exec -u root -it CONTAINER_ID tail -n 1000 -f /logs/ postgrescoingecko.log`

### play
- curl -I http://localhost:8080/postgres-coingecko-coins-list/v1/version
- curl -I http://localhost:8080/postgres-coingecko-coins-list/v1/coinslist
