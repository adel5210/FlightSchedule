FROM openjdk:17-alpine
COPY backend/target/*.jar run.jar
ENTRYPOINT [ "java","-jar","/run.jar"]
