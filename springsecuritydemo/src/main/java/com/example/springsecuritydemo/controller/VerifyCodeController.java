package com.example.springsecuritydemo.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/verifyCode")
public class VerifyCodeController {
    @Autowired
    Producer producer;

    @GetMapping("/create")
    public void createVerifyCode(HttpSession httpSession, HttpServletResponse response) throws IOException {
        String code = producer.createText();
        BufferedImage image = producer.createImage(code);
        httpSession.setAttribute("code", code);
        response.addCookie(new Cookie("code", code));

        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }

}
