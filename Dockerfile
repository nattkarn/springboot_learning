FROM openjdk:21-oracle
COPY target/app.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]