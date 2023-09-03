package com.grm.productDelivery.controller;

import com.grm.productDelivery.dto.UserDto;
import com.grm.productDelivery.services.Interfaces.UserService;
import com.grm.productDelivery.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping(value = "/user")
    public String wish(){
        return "Welcome to GRM Project ";
    }

    /**
     * This API is for to register user.
     * @param userDto
     * @param errors
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto, Errors errors) {
        log.info("inside UserController.registerUser() Begins");
        try {
            userValidator.validate(userDto,errors);
            if(errors.hasErrors()){
                log.error("This User can't be registered.");
                throw new Exception("This User can't be registered.");
            }
            UserDto userResponseDto = userService.create(userDto);
            log.info("inside UserController.registerUser() End's");
            return new ResponseEntity<UserDto>(userResponseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("This User can't be registered Or already Exist");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
