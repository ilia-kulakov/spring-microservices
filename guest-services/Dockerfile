FROM eclipse-temurin:8-jre-jammy
VOLUME /tmp

LABEL group="business-services"
LABEL description="REST application demonstrates features of Eureka client, Servo metrics, and external configs"

COPY target/guest-services-0.0.1-SNAPSHOT.jar /tmp/app.jar

EXPOSE 8810
ENTRYPOINT ["java", "-jar", "-DCONFIG_SERVER_URI=http://config-server:9000", "-DEUREKA_URI=http://eureka-server:8761/eureka", "-DZIPKIN_URL=http://zipkin:9411", "-Dserver.port=8810", "/tmp/app.jar"]