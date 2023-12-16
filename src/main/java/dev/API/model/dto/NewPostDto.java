package dev.API.model.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class NewPostDto {

    @NotBlank
    private String title;

    @NotBlank
    private String body;
 
}
