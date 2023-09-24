package com.grm.productDelivery.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ProductDto {
    @NotBlank(message = "skuName is required")
    private String skuName;

    @NotNull(message = "originalCost is required")
    private float originalCost;

    @NotBlank(message = "categoryType is required")
    private String categoryType;

    @NotNull(message = "sellingCost is required")
    private float sellingCost;

    @NotNull(message = "status is required")
    private boolean status;

}
