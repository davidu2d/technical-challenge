package com.eds.technicalchallenge.controllers;

import com.eds.technicalchallenge.domain.user.UserAuthDTO;
import com.eds.technicalchallenge.domain.user.UserRegisterDTO;
import com.eds.technicalchallenge.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    @PostMapping("/token")
    public ResponseEntity token(@RequestBody @Valid UserAuthDTO userAuth){
        var usernamePassword = new UsernamePasswordAuthenticationToken(userAuth.username(), userAuth.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserRegisterDTO userRegister){
        this.userService.register(userRegister.toUser());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
