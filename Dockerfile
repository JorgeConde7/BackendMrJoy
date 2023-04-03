FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD

COPY src /build/src
COPY pom.xml /build/

WORKDIR /build/
RUN mvn package -DskipTests

RUN ls

FROM openjdk:8-jre-alpine

WORKDIR /app
CMD ls

COPY --from=MAVEN_BUILD /build/target/MrJoy-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "MrJoy-0.0.1-SNAPSHOT.jar"]