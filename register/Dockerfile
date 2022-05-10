FROM adoptopenjdk/openjdk11:ubi
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY target/*.jar register.jar
EXPOSE 9102
CMD ["java", "-jar", "register.jar"]