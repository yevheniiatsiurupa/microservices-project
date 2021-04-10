FROM openjdk:11-jdk-slim

ADD ./build/libs/gateway-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,address=6060,suspend=n", "-jar", "/app/gateway-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080
EXPOSE 6060
