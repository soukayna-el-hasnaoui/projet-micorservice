FROM adoptopenjdk/openjdk11:ubi
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY target/*.jar topic.jar
EXPOSE 5002
CMD ["java", "-jar", "topic.jar"]