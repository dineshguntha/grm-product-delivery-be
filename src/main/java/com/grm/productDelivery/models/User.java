package com.grm.productDelivery.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author timbernerslee
 */
@Document(collection = "grm_product_user")
@Data
public class User {

    @Id
    private String id;
    private String firstName; // Required
    private String middleName; // Optional
    private String lastName; // Required
    private String loginName; // Required
    private String password;// Required
    private String emailId; // Optional
    private String phoneNumber; //Required
    @Indexed(unique = true)
    private List<String> roles; // Required;Admin, Owner, Delivery, Collection
    private String entityName; // Required
    @Indexed(unique = true)
    private String routeName;// Optional
}
