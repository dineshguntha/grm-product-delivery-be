package com.grm.productDelivery.models;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.*;

/**
 * @author timbernerslee
 */
@Document(collection = "customer")
@Data
public class Customer {
    @Id
    private String customerId;
    private String name;
    private String address;
    private String mobile;
    private String email;
    private String longitude;
    private String latitude;
}
