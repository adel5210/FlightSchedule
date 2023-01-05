FROM openjdk:19
COPY backend/target/*.jar run.jar
ENTRYPOINT [ "java","-jar","/run.jar"]
