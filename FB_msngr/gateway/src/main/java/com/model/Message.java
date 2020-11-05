package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
JSONObject message=new JSONObject("{\n" +
        "  \"messaging_type\": \"RESPONSE\",\n" +
        "  \"recipient\": {\n" +
        "    \"id\": \""+iu+"\"\n" +
        "  },\n" +
        "  \"message\": {\n" +
        "    \"text\": \""+msg+"\"\n" +
        "  }\n" +
        "}");*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    public String messaging_type;
    public String recipient;
    public String id;
    public String message;
    public String text;
}
