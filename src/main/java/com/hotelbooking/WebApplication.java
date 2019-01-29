package com.hotelbooking;

import com.hotelbooking.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAutoConfiguration
public class WebApplication {

    public static void main(String[] args) {

        SpringApplication.run(WebApplication.class, args);

        final String REST_SERVICE_URI = "http://localhost:8082";


        System.out.println("Testing getUser API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(REST_SERVICE_URI + "/user/1", User.class);
        System.out.println(user);

    }

}

