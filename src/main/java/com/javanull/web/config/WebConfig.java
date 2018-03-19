package com.javanull.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by lizhiming on 2018/3/14.
 */
@Component
public class WebConfig {
    @Value("${name}")
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
