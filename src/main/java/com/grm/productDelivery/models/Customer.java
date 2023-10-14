package com.grm.productDelivery.models;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.*;

import javax.validation.constraints.*;

/**
 * @author timbernerslee
 */
@Document(collection = "customer")
@Data
public class Customer {
    @Id
    private String customerId;
    @NotNull
    @NotBlank(message = "Name is required.")
    private String name;
    @NotNull
    @NotBlank(message = "Address is required.")
    private String address;
    @NotNull
    @NotBlank(message = "Mobile is required.")
    private String mobile;
    @NotNull
    @NotBlank(message = "Email is required.")
    private String email;
    @NotNull
    @NotBlank(message = "Longitude is required.")
    private String longitude;
    @NotNull
    @NotBlank(message = "Latitude is required.")
    private String latitude;
}
