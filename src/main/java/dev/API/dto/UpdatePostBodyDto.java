package dev.API.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdatePostBodyDto {
    @NotBlank
    private String body;
}
