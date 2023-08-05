package com.eds.technicalchallenge.domain.user;

import java.time.LocalDateTime;

public record UserRegisterDTO(String username, String password, UserRole role) {
    public User toUser(){
        return new User(username, password, role, LocalDateTime.now());
    }
}
