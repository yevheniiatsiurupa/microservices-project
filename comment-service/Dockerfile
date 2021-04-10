FROM openjdk:11-jdk-slim

ADD ./build/libs/comment-service-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,address=6061,suspend=n", "-jar", "/app/comment-service-0.0.1-SNAPSHOT.jar"]

EXPOSE 8081
EXPOSE 6061
