package dev.API.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateTitle {

    @NotBlank
    private String title;
}