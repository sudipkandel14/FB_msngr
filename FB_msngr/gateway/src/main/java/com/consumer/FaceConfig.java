package com.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FaceConfig {
    @Value("${accesstoken}")
    public String accessToken;

    @Value("${uri}")
    public String uri;
}
