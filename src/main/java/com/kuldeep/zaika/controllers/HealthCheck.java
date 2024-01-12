package com.kuldeep.zaika.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class HealthCheck {
    @GetMapping("")
    ResponseEntity<String> ping(){
        return new ResponseEntity<>("sab badhiya bhai", HttpStatus.OK);
    }
}