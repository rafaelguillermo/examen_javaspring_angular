FROM openjdk:11.0.11-jdk-slim
COPY "./target/demoIP-0.0.1-SNAPSHOT.jar" "demoIP.jar"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demoIP.jar"]