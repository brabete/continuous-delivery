FROM gradle:5.6.4-jdk8 as build

RUN mkdir -p /app
WORKDIR /app
COPY . .

RUN gradle bootJar

FROM adoptopenjdk:8-jre-openj9

COPY --from=build /app/build/libs/continuous-delivery-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app

CMD ["java", "-jar", "continuous-delivery-0.0.1-SNAPSHOT.jar"]