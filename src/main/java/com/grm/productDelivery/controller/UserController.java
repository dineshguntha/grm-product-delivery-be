package com.grm.productDelivery.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping(value = "/user")
    public String wish(){
        return "Welcome to GRM Project ";
    }
}
