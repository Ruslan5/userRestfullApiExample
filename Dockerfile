FROM maven:3.8.1-adoptopenjdk-11 as builder

COPY ./ ./

#packaging my app
RUN mvn clean package

#the second stage of build, will use poenjdk 11 on alpine
FROM openjdk:11-ea-11-jdk-slim

#copy only the artifact from first stage and discard the rest
COPY --from=builder /target/userResfullApiExample-0.0.1-SNAPSHOT.jar /app.jar

CMD ["java", "-jar", "/app.jar"]
