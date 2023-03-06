FROM eclipse-temurin:8-jre-jammy
VOLUME /tmp

LABEL group="business-services"
LABEL description="REST application demonstrates features of Feign client in conjunction with Zuul Balancer"

COPY target/feign-client-0.0.1-SNAPSHOT.jar /tmp/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-DBALANCE_SERVER_URI=http://zuul-balance-server:8762", "-Dserver.port=8080", "/tmp/app.jar"]