# Docker
Build and deploy the app using Docker instructions

### build image from Dockerfile
`./mvnw clean package`
`docker build --no-cache -t postgrescoingecko:v0.0.1 . -f Dockerfile_api`

### run image created from Dockerfile
`docker run --rm --name postgrescoingecko -p 8080:8080 --network=v1_postgres-pgadmin postgrescoingecko:v0.0.1`

### access the image as root
`docker run -it postgrescoingecko:v0.0.1 /bin/sh`
`cat /etc/os-release`

### access the container as root
`docker container ls docker exec -u root -it CONTAINER_ID /bin/sh`

### logs from the container
`tail -n 1000 -f /logs/ postgrescoingecko.log`

### logs
`docker exec -u root -it CONTAINER_ID tail -n 1000 -f /logs/ postgrescoingecko.log`

### play
curl -I http://172.16.128.130:8080/postgrescoingecko/v1/blocks
curl -I http://172.16.128.130:8080/postgrescoingecko/v1/version