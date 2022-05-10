FROM adoptopenjdk/openjdk11:ubi
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY target/*.jar message.jar
EXPOSE 5004
CMD ["java", "-jar", "message.jar"]