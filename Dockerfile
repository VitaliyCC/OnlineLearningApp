FROM openjdk:11
ADD /target/OnlineLearning-0.0.1-SNAPSHOT.war OnlineLearning.war
COPY src/main/resources src/main/resources
ENTRYPOINT ["java", "-jar", "OnlineLearning.war"]
EXPOSE 8080
