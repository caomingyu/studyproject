package com.example.springsecuritydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello Study Spring Security";
    }

    @PostMapping("/loginSuccess")
    public String loginSuccess(){
        return "redirect:/hello";
    }

    @GetMapping("/loginFailure")
    public String loginFailure(HttpServletResponse response) throws IOException {
        response.sendRedirect("/login.html");
        return null;
    }
}
