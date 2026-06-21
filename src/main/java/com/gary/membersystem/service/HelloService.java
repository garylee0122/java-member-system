package com.gary.membersystem.service;

import org.springframework.stereotype.Service;

@Service //@Service : 意思就是請Spring幫我建立Service物件，並放到 IOC Container
public class HelloService {

    public HelloService() {
        System.out.println("HelloService 建立了");
    }

    public String hello() {
        return "Hello Spring Boot";
    }

}
