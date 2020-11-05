package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conversation {

    ConvReq convReq;
    Visitor visitor;
   public static List<ConvResp>convResps= new ArrayList<>();
}
