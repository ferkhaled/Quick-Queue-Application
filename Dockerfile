FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY build/libs/quick-queue-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT java -jar -Dspring.datasource.url=jdbc:postgresql://${DB_HOST}:5432/postgres?currentSchema=quick_queue -Dspring.datasource.username=${DB_USER} -Dspring.datasource.password=${DB_PW} ./app.jar
