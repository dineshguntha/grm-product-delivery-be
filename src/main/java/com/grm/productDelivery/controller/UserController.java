package com.grm.productDelivery.controller;

import com.grm.productDelivery.dto.UserDto;
import com.grm.productDelivery.exceptions.ResourceNotFoundException;
import com.grm.productDelivery.services.Interfaces.UserService;
import com.grm.productDelivery.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping(value = "/user")
    public String wish() {
        return "Welcome to GRM Project ";
    }

    /**
     * This API is for to register user.
     *
     * @param userDto
     * @param errors
     * @return
     */
    @PostMapping("/registerUser")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto, Errors errors) {
        log.info("inside UserController.registerUser() Begins");
        try {
            userValidator.validate(userDto, errors);
            if (errors.hasErrors()) {
                log.error("This User can't be registered.");
                throw new Exception("This User can't be registered.");
            }
            UserDto userResponseDto = userService.create(userDto);
            log.info("inside UserController.registerUser() End's");
            return new ResponseEntity(userResponseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("This User can't be registered Or already Exist");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "This User can't be registered Or already Exist", e);
        }
    }


    /**
     * @param id
     * @param userDto
     * @param errors
     * @return
     */
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(value = "id") String id,
                                              @Valid @RequestBody UserDto userDto, Errors errors) {
        log.info("inside UserController.updateUser() Begins");
        try {
            userValidator.validate(userDto, errors);
            UserDto updatedUser = userService.updateUser(id, userDto);
            log.info("inside UserController.updateUser() End's");
            return new ResponseEntity(updatedUser, HttpStatus.OK);
        } catch (ResourceNotFoundException rnfe) {
            log.error("This User can't be updated Or Nor Exist " + rnfe.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User Not Found", rnfe);
        }
    }


    /**
     * @param id
     * @return
     */
    @DeleteMapping("/deleteUser/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") @NonNull String id) {
        log.info("inside UserController.deleteUser() Begins");
        try {
            userService.deleteUser(id);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            log.info("inside UserController.deleteUser() End's");
            return response;
        } catch (ResourceNotFoundException rnfe) {
            log.error("This User can't be Deleted Or Nor Exist " + rnfe.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User Not Found", rnfe);
        }
    }
}


























































































































































































































