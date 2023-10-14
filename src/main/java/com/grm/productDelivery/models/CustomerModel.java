package com.grm.productDelivery.models;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.*;
import org.springframework.data.mongodb.core.mapping.*;

import java.time.*;
import java.util.*;

/**
 * @author timbernerslee
 */
@Document(collection = "customer-model")
@Data
public class CustomerModel {
    @Id
    private String id;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
    private String entityName;
    @Indexed(unique = true)
    private String entityId;
    @Indexed(unique = true)
    private String routeName;
    @Indexed(unique = true)
    private List<Customer> customers;
}
