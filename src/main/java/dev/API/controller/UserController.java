package dev.API.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.API.dto.SigninDto;
import dev.API.dto.SignupDto;
import dev.API.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    

    private final AuthenticationService authService;


    @PostMapping("/signup")
    public ResponseEntity<?> sigup(@RequestBody @Valid SignupDto dto){
        return authService.sigup(dto);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody @Valid SigninDto dto){
        return authService.signin(dto);
    }


}
