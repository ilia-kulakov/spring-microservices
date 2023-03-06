FROM eclipse-temurin:8-jre-jammy
VOLUME /tmp

LABEL group="platform-services"
LABEL description="Eureka Descovery Server"

COPY target/eureka-server-0.0.1-SNAPSHOT.jar /tmp/app.jar

EXPOSE 8761
ENTRYPOINT ["java", "-jar", "-DZIPKIN_URL=http://zipkin:9411", "/tmp/app.jar"]