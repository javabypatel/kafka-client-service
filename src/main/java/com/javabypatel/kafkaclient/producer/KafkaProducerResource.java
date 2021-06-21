package com.javabypatel.kafkaclient.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javabypatel.kafkaclient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("kafka")
public class KafkaProducerResource {

    @Autowired
    private KafkaTemplate<String, byte[]> kafkaTemplate;

    private static final String TOPIC = "test-topic";

    @GetMapping("/publish/{name}")
    public String pushMessage(@PathVariable("name") final String name) throws JsonProcessingException {
        byte[] data = getUserBytes(name);
        ListenableFuture<SendResult<String, byte[]>> future = kafkaTemplate.send(TOPIC, data);
        future.addCallback(new KafkaProducerMessageCallbackHandler());
        return "Request for pushing message placed successfully, Check KafkaProducerMessageCallbackHandler for result.";
    }

    private byte[] getUserBytes(String name) throws JsonProcessingException {
        return new ObjectMapper()
                .writeValueAsString(new User(name, "Technology", 12000L))
                .getBytes(StandardCharsets.UTF_8);
    }
}