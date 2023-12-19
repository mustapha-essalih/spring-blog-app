package dev.API.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.API.dto.DeletedUserDto;
import dev.API.service.AdminService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/controle")
public class AdminController {
    
    @Autowired
    private AdminService adminService;

    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/dleteUser/{username}")
    public ResponseEntity<?> deleteUser(@RequestBody @Valid DeletedUserDto dto){
        try {
            return adminService.deleteUser(dto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<?> deletePost(@RequestBody @PathVariable("id") Integer id){
        try {
            return adminService.deletePost(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}

    
    