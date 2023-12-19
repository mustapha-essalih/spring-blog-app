package dev.API.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class NewPostDto {

    @NotBlank
    private String title;

    @NotBlank
    private String body;
 
    @NotNull
    private List<String> tags;
}