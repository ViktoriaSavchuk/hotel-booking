package com.hotelbooking.controller;

import com.hotelbooking.WebApplication;
import com.hotelbooking.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.junit.Assert.*;

/*@EnableWebMvc
@Configuration
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)*/
public class AppRequestControllerTest {

    /*private static final Logger logger = LoggerFactory.getLogger(AppRequestController.class);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setMockMvc(){
        mockMvc= MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }*/

    /*@Test
    public void updateReservations() {
*/
  //  }

    public static final String REST_SERVICE_URI = "http://localhost:8082";

    @Test
    public void getUser() {
        System.out.println("Testing getUser API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(REST_SERVICE_URI+"/user/1", User.class);
        System.out.println(user);
        //String url="/user/1";
        /*MvcResult mvcResult=mockMvc
                .perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        //logger.info(String.valueOf(mvcResult.getResponse().getContentAsString()));
        String responseAsJson="{\"userId\":1,\"name\":\"name1\",\"surname\":\"surname1\",\"phone\":\"067 067 067 67\"}";
        Assert.assertEquals("response does not match", mvcResult.getResponse().getContentAsString(), responseAsJson);*/
    }
/*
    @Test
    public void createUser() {

    }

    @Test
    public void getUserReservation() {
    }

    @Test
    public void getRoomByDate() {
    }

    @Test
    public void getRoomByCategory() {
    }

    @Test
    public void createReserv() {
    }*/
}