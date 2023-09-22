package com.grm.productDelivery.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "Product_Template")
public class ProductTemplate {
    @Id
    private String id;
    private String createdAt;
    private String createdBy;
    private String modifiedBy;
    private String entityName;
    private String entityId;
    private String routeName;
    private String userId;

    private List<Product> products;

}
