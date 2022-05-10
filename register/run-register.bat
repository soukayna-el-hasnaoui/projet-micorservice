mvn package
docker build -t register:1 .
docker rm register
docker run -d --name register -p 9102:9102 register:1
docker ps -a
docker stop register
docker rm register