For building the application jar file, this should create kafka-client-service-1.0-SNAPSHOT.jar
mvn clean install

For building docker image
docker build -t javabypatel/kafka-client-service:latest .

For deploying Kafka and Zookeeper, run below command,
oc process -f openshift/kafka-zookeeper-template.yaml -p SUFFIX=-jayesh -p ENVIRONMENT=testing | oc create -f -

For deleting the Kafka and Zookeeper deployment, run below command,
oc process -f openshift/kafka-zookeeper-template.yaml -p SUFFIX=-jayesh -p ENVIRONMENT=testing | oc delete -f -

Check the logs on init container
kubectl logs <pod-name> -c <init-container-name>
kubectl logs kafka-client-service-1-rbctf -c import-kafka-cert-to-truststore


