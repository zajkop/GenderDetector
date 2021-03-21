FROM amazoncorretto:11-alpine-jdk
ARG JAR_FILE=build/libs/genderdetector-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} genderdetector.jar
ENTRYPOINT ["java","-jar","/genderdetector.jar"]