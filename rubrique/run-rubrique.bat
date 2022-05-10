mvn package
docker build -t rubrique:1 .
docker rm rubrique
docker run -d --name rubrique -p 5002:5002 rubrique:1
docker ps -a
docker stop rubrique
docker rm rubrique