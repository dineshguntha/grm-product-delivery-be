package com.grm.productDelivery.dto;

import com.grm.productDelivery.models.*;
import lombok.*;

import javax.validation.constraints.*;
import java.time.*;
import java.util.*;

/**
 * @author timbernerslee
 */

@Data
public class CustomerTemplate {

    private String id;

    @NotNull
    @NotBlank(message = "The createdAt is required.")
    private LocalDateTime createdAt;

    @NotNull
    @NotBlank(message = "The createdBy is required.")
    private String createdBy;

    @NotNull
    @NotBlank(message = "The modifiedAt is required.")
    private LocalDateTime modifiedAt;

    @NotNull
    @NotBlank(message = "The modifiedBy is required.")
    private String modifiedBy;

    @NotNull
    @NotBlank(message = "The entityName is required.")
    private String entityName;

    @NotNull
    @NotBlank(message = "The entityId is required.")
    private String entityId;

    @NotNull
    @NotBlank(message = "The routeName is required.")
    private String routeName;

    @NotEmpty
    @NotNull
    @NotBlank(message = "Customers are required.")
    private List<Customer> customers;
}
