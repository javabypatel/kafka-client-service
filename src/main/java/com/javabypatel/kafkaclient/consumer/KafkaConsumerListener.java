package com.javabypatel.kafkaclient.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerListener {
    private static final String TOPIC = "test-topic";
    private static final String GROUP_ID = "test-group-id";

    @KafkaListener(topics = TOPIC, groupId = GROUP_ID)
    public void listen(ConsumerRecord<String, byte[]> record) {
        System.out.println("Received Message from topic " + TOPIC + " Record :" + record);
    }
}
