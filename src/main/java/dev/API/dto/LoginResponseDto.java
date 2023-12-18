package dev.API.dto;

import lombok.ToString;

@ToString
public class LoginResponseDto {
    private String username;
    private String toekn;
    public LoginResponseDto(String username, String toekn) {
        this.username = username;
        this.toekn = toekn;
    }

    
}
