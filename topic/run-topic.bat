mvn package
docker build -r topic:1 .
docker rm topic
docker run -d --name topic -p 5003:5003 topic:1
docker ps -a
docker stop topic
docker rm topic