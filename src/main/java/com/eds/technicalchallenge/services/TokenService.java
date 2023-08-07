package com.eds.technicalchallenge.services;

import com.eds.technicalchallenge.domain.user.User;

public interface TokenService {
    String generateToken(User user);
    String validateToken(String token);
}
