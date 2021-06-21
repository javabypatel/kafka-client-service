FROM adoptopenjdk/openjdk11

USER root

COPY target/*.jar /kafka-client-service/
RUN chmod -R 777 /kafka-client-service
RUN mv /kafka-client-service/kafka-client-service*.jar /kafka-client-service/kafka-client-service.jar

WORKDIR /kafka-client-service

ENTRYPOINT java -jar kafka-client-service.jar