package com.example.demo.webservice;

import org.springframework.stereotype.Component;

@Component
public class TestServiceImpl implements TestWebService {


    @Override
    public String sendMessage(String name) {

        return "hello webService"+ name;
    }
}
