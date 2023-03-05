FROM eclipse-temurin:8-jre-jammy
VOLUME /tmp

LABEL group="business-services"
LABEL description="REST application demonstrates features of Eureka client and external configs"

COPY target/room-services-0.0.1-SNAPSHOT.jar /tmp/app.jar

EXPOSE 8820
ENTRYPOINT ["java", "-jar", "-DCONFIG_SERVER_URI=http://config-server:9000", "-DEUREKA_URI=http://eureka-server:8761/eureka", "-Dserver.port=8820", "/tmp/app.jar"]