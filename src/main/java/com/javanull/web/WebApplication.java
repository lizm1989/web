package com.javanull.web;

import com.javanull.web.config.UserApplicationListener;
import com.javanull.web.config.UserUtil;
import com.javanull.web.config.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebApplication {



    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
//        SpringApplication springApplication = new SpringApplication(WebApplication.class);
//        springApplication.addListeners(new UserApplicationListener());
//        springApplication.run(args);
    }
}
