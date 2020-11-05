package com.producer.demo.consumer.controller;

import com.producer.demo.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private Producer producer;

    @PostMapping("/v1/kafkatest")
    public void post(@RequestBody String msg) {
        producer.produceMessage(msg);
    }

}
