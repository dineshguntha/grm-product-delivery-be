package com.grm.productDelivery.controller;


import com.grm.productDelivery.dto.JWTUserRequest;
import com.grm.productDelivery.models.JWTUserInfo;
import com.grm.productDelivery.services.JWTUserInfoService;
import com.grm.productDelivery.services.JwtService;
import com.grm.productDelivery.dto.JWTAuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class JWTAuthenticationController {
    @Autowired
    private JwtService jwtService;


    @Autowired
    private JWTUserInfoService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody JWTUserRequest req) {
        System.out.println("Adding new user");
        JWTUserInfo ju=new JWTUserInfo(req.getName(),req.getEmail(),req.getPassword(),req.getRoles());

        return service.addUser(ju);
    }
    @PostMapping("/token")
    public String authenticateAndGetToken(@RequestBody JWTAuthRequest authRequest) {
        System.out.println("Authenticate token");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
