FROM openjdk:11-jdk-slim

ADD ./build/libs/auth-service-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,address=6064,suspend=n", "-jar", "/app/auth-service-0.0.1-SNAPSHOT.jar"]

EXPOSE 8084
EXPOSE 6064
