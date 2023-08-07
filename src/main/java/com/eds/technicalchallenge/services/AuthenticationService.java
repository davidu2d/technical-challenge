package com.eds.technicalchallenge.services;

import com.eds.technicalchallenge.domain.token.TokenResponseDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

    UserDetails loadUserByUsername(String username);

    TokenResponseDTO authenticate(String username, String password);
}
