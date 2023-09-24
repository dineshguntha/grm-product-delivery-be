package com.grm.productDelivery.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ProductTemplateDto {

    private String id;

    @NotBlank(message = "createdBy is required")
    @NotNull(message = "createdBy is required. It should not be null")
    private String createdBy;

    @NotBlank(message = "entityName is required")
    @NotNull
    private String entityName;

    @NotBlank(message = "entityId is required")
    @NotNull
    private String entityId;

    @NotBlank(message = "routeName is required")
    @NotNull
    private String routeName;

    @NotBlank(message = "userId is required")
    @NotNull
    private String userId;

    @Valid
    @NotEmpty(message = "products must not be empty")
    @Size(min = 1, message = "At least one product must be provided")
    private List<ProductDto> products;
}
