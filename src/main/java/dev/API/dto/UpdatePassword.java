package dev.API.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdatePassword {
    
    @NotBlank
    private String currentPassword;

    @NotBlank
    private String newPassword;
}
