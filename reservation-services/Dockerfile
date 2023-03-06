FROM eclipse-temurin:8-jre-jammy
VOLUME /tmp

LABEL group="business-services"
LABEL description="REST application demonstrates features of Eureka client and external configs"

COPY target/reservation-services-0.0.1-SNAPSHOT.jar /tmp/app.jar

EXPOSE 8830
ENTRYPOINT ["java", "-jar", "-DCONFIG_SERVER_URI=http://config-server:9000", "-DEUREKA_URI=http://eureka-server:8761/eureka", "-DZIPKIN_URL=http://zipkin:9411", "-Dserver.port=8830", "/tmp/app.jar"]