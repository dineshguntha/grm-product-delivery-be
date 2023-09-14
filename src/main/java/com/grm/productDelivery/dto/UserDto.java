package com.grm.productDelivery.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

/**
 * @author timbernerslee
 */
@Data
public class UserDto {

    private String userId;

    @NotNull
    @NotBlank(message = "The First Name is required.")
    private String firstName;

    private String middleName;

    @NotNull
    @NotBlank(message = "The Last Name is required.")
    private String lastName;

    @NotNull
    @NotBlank(message = "The Login Name is required.")
    private String loginName;


    @NotNull
    @NotBlank(message = "The Password is required.")
    private String password;

    @Email(message = "The email address is invalid.", flags = {Pattern.Flag.CASE_INSENSITIVE})
    private String emailId;

    @NotNull
    @NotBlank(message = "The Phone Number is required.")
    private String phoneNumber;

    @NotBlank(message = "Roles are required at least one.")
    @NotEmpty
    @NotNull
    private List<String> roles;

    @NotNull
    @NotBlank(message = "The Entity Name is required.")
    private String entityName;

    private String routeName;

}
