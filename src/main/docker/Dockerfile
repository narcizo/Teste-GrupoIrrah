FROM openjdk:20
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} application.jar
COPY pgdump/backup.sql /docker-pgdump/
ENTRYPOINT ["java", "-jar", "application.jar"]