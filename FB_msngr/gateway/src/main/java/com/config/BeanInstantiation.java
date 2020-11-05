package com.config;

import com.model.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanInstantiation {

    @Bean(name = "restTemplate")
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "faceBookResponse")
    public Message createMessafeResponse(){return new Message();}

}
