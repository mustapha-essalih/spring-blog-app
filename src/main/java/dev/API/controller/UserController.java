package dev.API.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.API.model.dto.LoginDto;
import dev.API.model.dto.NewAuthorDto;
import dev.API.model.dto.NewPostDto;
import dev.API.model.dto.UpdatePassword;
import dev.API.model.entity.RootUser;
import dev.API.service.AuthenticationService;
import dev.API.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authService;
    
    @PostMapping("/register")
    public ResponseEntity<?> registerNewAccount(@RequestBody @Valid NewAuthorDto dto){

        return authService.registerNewAccount(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto dto){
        
        return authService.login(dto);
    }

     @PatchMapping("/updatepassword")
    public  ResponseEntity<?>  updatePassword(@RequestBody UpdatePassword dto , Principal  client){
        return authService.updatePassword(dto.getCurrentPassword(), dto.getNewPassword() , client.getName());
    } 
}


