package com.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;



@Service
public class Producer {



    @Autowired
    private KafkaTemplate<String , String> kafkaTemplate ;

    public void produceMessage(String msg){
        kafkaTemplate.send("gateway-in-bound",msg);
    }

}
