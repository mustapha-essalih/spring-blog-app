package dev.API.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.API.model.dto.NewPostDto;
import dev.API.service.AuthorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    
    @Autowired
    private AuthorService authorService;

    @PostMapping("/newPost")
    public ResponseEntity<?> createPost(@RequestBody @Valid NewPostDto dto , Principal client ){
        return authorService.createPost(dto , client.getName());   
    }



}

 