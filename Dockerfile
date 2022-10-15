FROM openjdk:11
ADD /target/OnlineLearning-0.0.1-SNAPSHOT.war OnlineLearning.war
ENTRYPOINT ["java", "-jar", "OnlineLearning.war"]
EXPOSE 8080
