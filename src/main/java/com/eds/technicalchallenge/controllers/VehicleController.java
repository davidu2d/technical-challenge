package com.eds.technicalchallenge.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("vehicles")
@RequiredArgsConstructor
public class VehicleController {

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok("new vehicle");
    }
}
