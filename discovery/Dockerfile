FROM openjdk:11-jdk-slim

ADD ./build/libs/discovery-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,address=6063,suspend=n", "-jar", "/app/discovery-0.0.1-SNAPSHOT.jar"]

EXPOSE 8083
EXPOSE 6063
