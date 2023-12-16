package dev.API.model.dto;

import lombok.Getter;

@Getter
public class UpdatePassword {
    private String currentPassword;
    private String newPassword;
}
