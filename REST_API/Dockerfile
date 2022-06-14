FROM maven:3.8.3-jdk-11 as builder

WORKDIR /app
COPY ./ .

RUN mvn clean install -DskipTests

FROM adoptopenjdk/openjdk11:alpine-jre as runner

WORKDIR /home/app
COPY --from=builder /app/target/Monolithic_Shop-0.0.1-SNAPSHOT.jar /home/app
EXPOSE 8080
ENTRYPOINT ["java","-jar", "./Monolithic_Shop-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod"]
