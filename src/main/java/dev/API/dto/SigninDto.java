package dev.API.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SigninDto {
    
    @NotBlank
    private String username;
    
    @NotBlank
    private String password;
}
