package com.eds.technicalchallenge.domain.user;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record UserRegisterDTO(@NotBlank String username, @NotBlank String password, @NotBlank UserRole role) {
    public User toUser(){
        return new User(username, password, role, LocalDateTime.now());
    }
}
