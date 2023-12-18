package dev.API.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignupDto {
    
    @NotBlank
    private String username;

    @NotBlank
    @Email    
    private String email;

    @NotBlank
    private String password;
}
