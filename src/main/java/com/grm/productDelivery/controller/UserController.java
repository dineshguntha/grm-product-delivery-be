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
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/user")
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
        log.info("inside UserController.registerUser()");
        try {
            userValidator.validate(userDto, errors);
            if (errors.hasErrors()) {
                log.error("This User can't be registered.");
                throw new Exception("This User can't be registered.");
            }
            UserDto userResponseDto = userService.create(userDto);
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
        log.info("inside UserController.updateUser()");
        try {
            userValidator.validate(userDto, errors);
            UserDto updatedUser = userService.updateUser(id, userDto);
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

    /**
     * @param entityName
     * @return
     */
    @GetMapping("/getUsersByEntityName/{entityName}")
    public ResponseEntity<List<UserDto>> getUsersByEntityName(@PathVariable(value = "entityName") @NonNull String entityName) {
        log.info("inside UserController.getUsersByEntityName()");
        try {
            return new ResponseEntity<>(userService.getUsersListByEntityName(entityName), HttpStatus.OK);
        } catch (Exception exception) {
            log.error("This User is not Exist " + exception.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User Not Found with Entity Name", exception);
        }
    }

    /**
     * @param loginName
     * @return
     */
    @GetMapping("/getUserByLoginName/{loginName}")
    public ResponseEntity<UserDto> getUserByLoginName(@PathVariable(value = "loginName") @NonNull String loginName) {
        log.info("inside UserController.getUserByLoginName()");
        try {
            return new ResponseEntity<>(userService.getUserByLoginName(loginName), HttpStatus.OK);
        } catch (Exception exception) {
            log.error("This User is not Exist " + exception.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User Not Found with Login Name", exception);
        }
    }

    /**
     * @return
     */
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.info("inside UserController.getAllUsers()");
        try {
            return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Exceptions in getAllUsers" + exception.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Exceptions in getAllUsers", exception);
        }
    }
}


