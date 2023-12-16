package dev.API.model.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String username;
    private String token;

    public LoginResponseDto(String username , String token)
    {   
        this.username = username;
        this.token = token;
    }

}
