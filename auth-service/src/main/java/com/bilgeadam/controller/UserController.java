package com.bilgeadam.controller;

import com.bilgeadam.dto.request.DoLoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.DoLoginResponseDto;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    // ReturnType
    // -> returnCode-> error->9XXX -> 9001-> username and password error
    // -> success-> 1XXX -> 1000, 1100
    // Validation processes have to be done here.
    @PostMapping("/dologin")
    @Operation(summary = "This method will be used to login user")
    public ResponseEntity<DoLoginResponseDto> doLogin(@RequestBody @Valid DoLoginRequestDto dto){
        return ResponseEntity.ok(userService.findByUsernameAndPassword(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequestDto dto){
        // First step -> User has to login for authentication
        userService.saveReturnUser(dto);
        // Second step -> A request will send to User-Service to save user. Processes will be done based on response.
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findall")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }


}