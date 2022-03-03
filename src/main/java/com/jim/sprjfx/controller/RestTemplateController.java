package com.jim.sprjfx.controller;

import com.jim.sprjfx.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@Slf4j
@RestController
public class RestTemplateController {

    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @RequestMapping(value = "/ws",method = RequestMethod.GET)
    public User findById(@RequestBody JSONObject object){
        log.info(object.toString());
        User forObject = restTemplate.getForObject("http://localhost:8081/test", User.class);
        return forObject;
    }

}
