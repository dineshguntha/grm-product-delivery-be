package com.grm.productDelivery.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

/**
 * @author timbernerslee
 */
@Data
public class UserDto {

    @NotNull
    @NotBlank(message = "The First Name is required.")
    private String firstName;

    private String middleName;

    @NotNull
    @NotBlank(message = "The Last Name is required.")
    private String lastName;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

}
