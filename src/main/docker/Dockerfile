FROM openjdk:17-alpine
ENV PORT 8080
EXPOSE 8080
RUN mkdir /opt/app
ADD maven/technical-challenge.jar /opt/app
ENTRYPOINT ["java", "-jar", "/opt/app/technical-challenge.jar"]