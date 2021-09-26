package com.example.demo.controller;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.dto.Logindto;
import com.example.demo.dto.RegisterationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/Registeration")
public class RegisterationController {
    @Autowired
    private UserService userService;

@PostMapping("/register")
public ResponseEntity<Object> addNewUser(@Valid @RequestBody RegisterationDto registerationDto){
    userService.addNewUser(registerationDto);
    return new ResponseEntity<>("account created successfuly",HttpStatus.ACCEPTED);
}

@PostMapping("/login")
public ResponseEntity<User> loginUser(@Valid @RequestBody Logindto logindto){
    User user = userService.loginUser(logindto.getUsername(),logindto.getPassword());
    if(user != null){
        return ResponseEntity.ok().body(user);
    }
    else {
         throw new UserNotFoundException("User doesn't exist");
    }
}

}
