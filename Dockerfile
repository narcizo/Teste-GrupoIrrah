FROM openjdk:20
EXPOSE 8081

ADD target/teste-irrah-docker.jar teste-irrah-docker.jar

ENTRYPOINT ["java", "-jar", "/teste-irrah-docker.jar"]