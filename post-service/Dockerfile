FROM openjdk:11-jdk-slim

ADD ./build/libs/post-service-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,address=6062,suspend=n", "-jar", "/app/post-service-0.0.1-SNAPSHOT.jar"]

EXPOSE 8082
EXPOSE 6062
