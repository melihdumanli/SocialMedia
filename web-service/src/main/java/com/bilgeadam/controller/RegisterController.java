package com.bilgeadam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class RegisterController {

    @PostMapping("/register")
    public Object register(String firstname, String lastname, String email, String password, String country, MultipartFile image) {
        ModelAndView model = new ModelAndView();
        model.setViewName("register");

        return model;
    }
}
