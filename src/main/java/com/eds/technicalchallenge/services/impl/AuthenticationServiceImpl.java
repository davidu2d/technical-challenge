package com.eds.technicalchallenge.services.impl;

import com.eds.technicalchallenge.domain.token.TokenResponseDTO;
import com.eds.technicalchallenge.domain.user.User;
import com.eds.technicalchallenge.repositories.UserRepository;
import com.eds.technicalchallenge.services.AuthenticationService;
import com.eds.technicalchallenge.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements UserDetailsService, AuthenticationService {

    private final UserRepository repository;
    @Autowired
    private AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    public TokenResponseDTO authenticate(String username, String password){
        var usernamePassword = new UsernamePasswordAuthenticationToken(username, password);
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = this.tokenService.generateToken((User) auth.getPrincipal());
        return TokenResponseDTO.builder().token(token).build();
    }

}
