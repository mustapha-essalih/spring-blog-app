package dev.API.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.API.dto.NewPostDto;
import dev.API.dto.UpdatePostBodyDto;
import dev.API.dto.UpdateTitle;
import dev.API.service.AuthorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    
    @Autowired
    private AuthorService authorService;

    @PostMapping("/newPost")
    public ResponseEntity<?> createPost(@RequestBody @Valid NewPostDto dto , Principal client ){

        try {
            return authorService.createPost(dto , client.getName());   
            
        } catch (Exception e) {
            
            return ResponseEntity.badRequest().body("user not found");
        }
    }


    @PatchMapping("/updatePostTitle/{id}")
    public ResponseEntity<?> updatePostTitle(@RequestBody @Valid UpdateTitle dto , @PathVariable("id") Integer id){

        try {
            return authorService.updateTitle(dto , id);   
        } catch (Exception e) {
            
            return ResponseEntity.badRequest().body("");
        }
    }
 
    @PatchMapping("/updatePostBody/{id}")
    public ResponseEntity<?> updatePostBody(@RequestBody @Valid UpdatePostBodyDto dto , @PathVariable("id") Integer id){

        try {
            return authorService.updatePostBody(dto , id);   
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("");
        }
    }
}


 
    