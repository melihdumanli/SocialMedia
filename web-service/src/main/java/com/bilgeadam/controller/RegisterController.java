package com.bilgeadam.controller;

import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.manager.AuthServiceManager;
import com.bilgeadam.service.S3ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
@Slf4j
public class RegisterController {

    private final S3ManagerService s3ManagerService;

    private final AuthServiceManager authServiceManager;

    //http:localhost/register
    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public Object register(String firstname, String lastname, String email, String password, String country, MultipartFile image) {
        ModelAndView model = new ModelAndView();

        String profileId = authServiceManager.register(RegisterRequestDto.builder()
                .password(password)
                .email(email)
                .surname(lastname)
                .name(firstname)
                .country(country)
                .build()).getBody();

        try{
            s3ManagerService.putObject(profileId+".png",image);
            model.setViewName("login");
        }catch (Exception e){
            log.error("Uploading Image Failed...: "+e.getMessage());
            model.setViewName("register");
        }
        return model;
    }
}
