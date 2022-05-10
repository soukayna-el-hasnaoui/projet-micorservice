FROM adoptopenjdk/openjdk11:ubi
ENV APP_HOME=/target
WORKDIR $APP_HOME
COPY target/*.jar api-gateway.jar
EXPOSE 9004
CMD ["java", "-jar", "api-gateway.jar"]