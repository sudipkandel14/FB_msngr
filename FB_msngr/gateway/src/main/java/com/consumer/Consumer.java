package com.consumer;

import com.model.Message;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@Service
public class Consumer {

    @Autowired
   private FaceConfig faceConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Message faceBookResponse;

    @KafkaListener(topics = "gateway-out-bound",groupId = "abc")
    public void consumeMessage(String msg) throws IOException {

        final String uri = faceConfig.uri+faceConfig.accessToken;
        String iu= "3438190972919145";

        /*faceBookResponse.setMessaging_type("RESPONSE");
        faceBookResponse.setId(iu);
        faceBookResponse.setRecipient();*/
        JSONObject message=new JSONObject("{\n" +
                "  \"messaging_type\": \"RESPONSE\",\n" +
                "  \"recipient\": {\n" +
                "    \"id\": \""+iu+"\"\n" +
                "  },\n" +
                "  \"message\": {\n" +
                "    \"text\": \""+msg+"\"\n" +
                "  }\n" +
                "}");

        System.out.println("This is the message i had made "+ message);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request =
                new HttpEntity<String>(message.toString(), headers);
       // restTemplate.postForEntity(uri,request,Object.class);
        restTemplate.exchange(uri, HttpMethod.POST,request,String.class);

    }


}
