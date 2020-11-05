package com.controller;

import com.fasterxml.jackson.core.JsonParseException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Optional;


public class RequestValidator {

    public static boolean isJsonValid(String inputJosn) throws JsonParseException, IOException {
        JSONObject obj = new JSONObject(inputJosn);
        try {
            Optional<Object> message = Optional.ofNullable(obj.getJSONArray("entry")
                    .getJSONObject(0)
                    .getJSONArray("messaging")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("text"));
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
