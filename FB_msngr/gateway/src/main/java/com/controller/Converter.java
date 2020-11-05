package com.controller;

import com.model.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Converter {

    public  String change(String data) throws IOException {

        User user= new User("Sudip","Kandel","male","sk14@gmail.com");
        JSONObject obj = new JSONObject(data);
        String sid = obj.getJSONArray("entry")
                .getJSONObject(0)
                .getJSONArray("messaging")
                .getJSONObject(0)
                .getJSONObject("sender")
                .getString("id");
        String  timestamp = String.valueOf(obj.getJSONArray("entry")
                .getJSONObject(0)
                .getJSONArray("messaging")
                .getJSONObject(0)
                .getInt("timestamp"));
        String  message = obj.getJSONArray("entry")
                .getJSONObject(0)
                .getJSONArray("messaging")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("text");
        Visitor visitor = new Visitor(sid,user);
        ConvResp convResp = new ConvResp(message,timestamp);
        ConvReq convReq = new ConvReq(1,message,timestamp);
        Conversation conversation = new Conversation();
        conversation.setConvReq(convReq);
        conversation.setVisitor(visitor);
        conversation.convResps.add(convResp);

        // Creating Object of ObjectMapper define in Jakson Api
        ObjectMapper Obj = new ObjectMapper();

        // get Oraganisation object as a json string
        String jsonStr = Obj.writeValueAsString(conversation);

        return jsonStr;
    }
}
