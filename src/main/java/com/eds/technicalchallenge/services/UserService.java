package com.eds.technicalchallenge.services;

import com.eds.technicalchallenge.domain.user.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);

    void register(User user);
}
