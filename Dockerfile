FROM openjdk:11
LABEL maintainer="debby.net"
ADD target/demo2-0.0.1-SNAPSHOT.jar demo2.jar
ENTRYPOINT ["java", "-jar", "demo2.jar"]