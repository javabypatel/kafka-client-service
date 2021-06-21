package com.javabypatel.kafkaclient.producer;

import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class KafkaProducerMessageCallbackHandler implements ListenableFutureCallback<SendResult<String, byte[]>> {

    @Override
    public void onSuccess(SendResult<String, byte[]> result) {
        System.out.println("Sent message with offset=[" + result.getRecordMetadata().offset() + "]");
    }

    @Override
    public void onFailure(Throwable ex) {
        System.out.println("Unable to send message due to :" + ex.getMessage());
    }
}
