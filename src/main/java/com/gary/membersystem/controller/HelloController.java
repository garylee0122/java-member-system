package com.gary.membersystem.controller;

import com.gary.membersystem.service.EmailService;
import com.gary.membersystem.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //@RestController:表示這是一個 API Controller
public class HelloController {

    private final HelloService helloService;
    private final EmailService emailService;

    public HelloController(HelloService helloService,
                           EmailService emailService) {
        this.helloService = helloService;
        this.emailService = emailService;
    }

    @GetMapping("/hello") //表示：用 HTTP GET 方法，URL 對應：/hello
    public String hello() {
        return helloService.hello();
    }

    @GetMapping("/email")
    public String email() {
        return emailService.sendMail();
    }
}
