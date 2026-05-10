package com.gen_4.horse_exchange.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;


@RestController
@CrossOrigin
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("users")
    public void getUsers() {
        return;
    }
    
}
