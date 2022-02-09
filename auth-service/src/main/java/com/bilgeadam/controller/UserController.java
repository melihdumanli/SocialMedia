package com.bilgeadam.controller;

import static com.bilgeadam.constant.RestApiUrls.*;
import com.bilgeadam.dto.request.DoLoginRequestDto;
import com.bilgeadam.dto.request.ProfileRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.DoLoginResponseDto;
import com.bilgeadam.manager.ProfileManager;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(VERSION + USER)
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ProfileManager profileManager;


    // ReturnType
    // -> returnCode-> error->9XXX -> 9001-> username and password error
    // -> success-> 1XXX -> 1000, 1100
    // Validation processes have to be done here.
    @PostMapping(DOLOGIN)
    @Operation(summary = "This method will be used to login user")
    public ResponseEntity<DoLoginResponseDto> doLogin(@RequestBody @Valid DoLoginRequestDto dto){
        return ResponseEntity.ok(userService.findByUsernameAndPassword(dto));
    }

    @PostMapping(REGISTER)
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequestDto dto){
        // First step -> User has to login for authentication
        User user = userService.saveReturnUser(dto);
        // Second step -> A request will send to User-Service to save user. Processes will be done based on response.
        profileManager.save(ProfileRequestDto.builder()
                        .authid(user.getId())
                        .email(dto.getEmail())
                        .firstname(dto.getName())
                        .lastname(dto.getSurname())
                        .country(dto.getCountry())
                        .city(dto.getCity())
                        .city(dto.getCity())
                        .gender(dto.getGender())
                .build());
        return ResponseEntity.ok().build();
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }


}