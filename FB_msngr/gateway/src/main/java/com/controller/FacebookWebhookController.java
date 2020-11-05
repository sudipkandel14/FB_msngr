package com.controller;


import javax.servlet.http.HttpServletRequest;

import com.producer.Producer;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/febtest/facebook/webhook")
public class FacebookWebhookController {
    @Autowired
    private Producer producer;

    @Autowired
    private Converter converter;

    private static final String MODE_SUBSCRIBE = "subscribe";

    @PostMapping( produces = "application/json")
    public ResponseEntity<String> messagesPost(@RequestBody String jsonRequest, @RequestHeader HttpHeaders headers)
            throws Throwable {
        RequestValidator requestValidator = new RequestValidator();

        System.out.println("JSON Request from Facebook: " + jsonRequest);
        JSONObject obj = new JSONObject(jsonRequest);
        String sid = obj.getJSONArray("entry")
                .getJSONObject(0)
                .getJSONArray("messaging")
                .getJSONObject(0)
                .getJSONObject("sender")
                .getString("id");
        if(requestValidator.isJsonValid(jsonRequest)){
          producer.produceMessage(converter.change(jsonRequest));
        }
        return new ResponseEntity<String>("Request Accepted. We are on it.", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> subscribe(@RequestHeader HttpHeaders headers,
                                       @RequestParam(value = "hub.mode") String hubMode,
                                       @RequestParam(value = "hub.challenge") String hubChallenge, HttpServletRequest request) {
        if (MODE_SUBSCRIBE.equals(hubMode)) {
            return new ResponseEntity<String>(hubChallenge, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("GET request NOT a subscribe mode, hence NOT valid",
                    HttpStatus.UNAUTHORIZED);
        }
    }

}
