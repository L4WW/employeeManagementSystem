FROM openjdk
EXPOSE 8080
VOLUME /tmp
ARG JAR_FILE=target/employee-management-system-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]