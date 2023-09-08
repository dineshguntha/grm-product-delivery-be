package com.grm.productDelivery.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "entity")
public class Entity {
    @Id
    private String id;

    @Indexed(unique = true)
    private String name;
    private String gst;
    private String accountNumber;

    public Entity(String name, String gst, String accountNumber) {
        this.name = name;
        this.gst = gst;
        this.accountNumber = accountNumber;
    }
    public Entity(String id, String name, String gst, String accountNumber) {
        this.id = id;
        this.name = name;
        this.gst = gst;
        this.accountNumber = accountNumber;
    }

}
