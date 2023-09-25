package com.grm.productDelivery.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Product")
public class Product {
    private String id;
    private String skuName;
    private float originalCost;
    private float  sellingCost;
    private String categoryType;
    private boolean status;

}
