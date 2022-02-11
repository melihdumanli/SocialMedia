package com.bilgeadam.controller;

import com.bilgeadam.model.LoginPageModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class LoginController {

    //localhost/login
    @GetMapping("login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("model", LoginPageModel.builder().title("Become a Member").build());
        return modelAndView;
    }
}
