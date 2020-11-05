package com.producer.demo.consumer;

import com.producer.demo.producer.Producer;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @Autowired
    Producer producer;
    @KafkaListener(topics = "gateway-in-bound", groupId = "abc")
    public void consumeMessage(String msg) {
        System.out.println("INFO-1------ " + msg);
        JSONObject obj = new JSONObject(msg);
        String sid = obj.getJSONObject("convReq")
                .getString("message");
        System.out.println("value of sid is "+sid);
        producer.produceMessage("You send "+ sid+ " to me");

    }

    @KafkaListener(topics = "gateway-out-bound", groupId = "abc")
    public void consumeMessage2(String msg) throws InterruptedException {
        Thread.sleep(15000);
        System.out.println("INFO-2------ " + msg);
    }


}
