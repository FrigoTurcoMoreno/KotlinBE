FROM gradle:jdk21 AS build

WORKDIR /gradle

COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle.kts build.gradle.kts
COPY settings.gradle.kts settings.gradle.kts
COPY src src

RUN chmod +x gradlew

RUN ./gradlew clean build


FROM openjdk:21-jdk

WORKDIR /be

COPY --from=build /gradle/build/libs/*.jar /be/kotlin-be.jar

EXPOSE 8080

ENV DB_URL=jdbc:mysql://db:3306/basic_be
ENV DB_USER=user
ENV DB_PASSWORD=root
ENV JWT_SECRET=5817de32-b039-49fc-bfa7-782df4bb5a88

CMD ["java", "-jar", "/be/kotlin-be.jar"]