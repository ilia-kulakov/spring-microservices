FROM eclipse-temurin:8-jre-jammy
VOLUME /tmp

LABEL group="platform-services"
LABEL description="Spring Cloud external config server"

COPY target/config-server-0.0.1-SNAPSHOT.jar /tmp/app.jar

EXPOSE 9000
ENTRYPOINT ["java", "-jar", "/tmp/app.jar"]