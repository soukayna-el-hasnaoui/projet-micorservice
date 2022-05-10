mvn package
docker build -r message:1 .
docker rm message
docker run -d --name message -p 5004:5004 message:1
docker ps -a
docker stop message
docker rm rubrique