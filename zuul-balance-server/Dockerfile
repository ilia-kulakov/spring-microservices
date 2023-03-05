FROM eclipse-temurin:8-jre-jammy
VOLUME /tmp

LABEL group="platform-services"
LABEL description="Zuul Balance Proxy Server"

COPY target/zuul-balance-server-0.0.1-SNAPSHOT.jar /tmp/app.jar

EXPOSE 8762
ENTRYPOINT ["java", "-jar", "-DEUREKA_URI=http://eureka-server:8761/eureka", "-Dserver.port=8762", "/tmp/app.jar"]