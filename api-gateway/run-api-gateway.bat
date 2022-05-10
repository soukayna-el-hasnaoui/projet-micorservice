mvn package
docker build -r api-gateway:1 .
docker rm api-gateway
docker run -d --name api-gateway -p 9004:9004 api-gateway:1
docker ps -a
docker stop api-gateway
docker rm rubrique