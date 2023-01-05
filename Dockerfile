FROM openjdk
COPY backend/target/*.jar run.jar
ENTRYPOINT [ "java","-jar","/run.jar"]
