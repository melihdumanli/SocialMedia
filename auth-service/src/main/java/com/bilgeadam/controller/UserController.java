package com.bilgeadam.controller;

import static com.bilgeadam.constant.RestApiUrls.*;
import com.bilgeadam.dto.request.DoLoginRequestDto;
import com.bilgeadam.dto.request.ProfileRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.DoLoginResponseDto;
import com.bilgeadam.manager.ProfileManager;
import com.bilgeadam.rabbitmq.model.Notification;
import com.bilgeadam.rabbitmq.producer.UserServiceProducer;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(VERSION + AUTH)
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ProfileManager profileManager;

    @Autowired
    UserServiceProducer userServiceProducer;


    // ReturnType
    // -> returnCode-> error->9XXX -> 9001-> username and password error
    // -> success-> 1XXX -> 1000, 1100
    // Validation processes have to be done here.
    @PostMapping(DOLOGIN)
    @Operation(summary = "This method will be used to login user")
    public ResponseEntity<DoLoginResponseDto> doLogin(@RequestBody @Valid DoLoginRequestDto dto){
        return ResponseEntity.ok(userService.findByUsernameAndPassword(dto));
    }

    @PostMapping("/sendmessage")
    public ResponseEntity<Void> sendMessage(String message){
        userServiceProducer.sendMessage(Notification.builder()
                .message(message)
                .build());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/validatetoken")
    @Operation(summary = "Token kontrolü için kullanılır.")
    public ResponseEntity<Boolean> verifyToken(String token){
        return ResponseEntity.ok(userService.verifyToken(token));
    }

    @PostMapping("/deleteuser")
    public ResponseEntity<Void> deleteUser(String message){
        userServiceProducer.deleteUser(Notification.builder()
                .message(message)
                .build());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/hello")
    public String hello(){
        log.info("This is welcome page");
        log.info("An error occurred.");
        return "Hello";
    }

    @PostMapping(REGISTER)
    public ResponseEntity<String > register(@RequestBody @Valid RegisterRequestDto dto){
        // First step -> User has to login for authentication
        User user = userService.saveReturnUser(dto);
        // Second step -> A request will send to User-Service to save user. Processes will be done based on response.
        ProfileRequestDto asdDto = ProfileRequestDto.builder()
                .authid(user.getId())
                .email(dto.getEmail())
                .firstname(dto.getName())
                .lastname(dto.getSurname())
                .country(dto.getCountry())
                .city(dto.getCity())
                .gender(dto.getGender())
                .build();
        String id = profileManager.save(asdDto).getBody();
        return ResponseEntity.ok(id);
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }


}