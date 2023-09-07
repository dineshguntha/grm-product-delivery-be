package com.grm.productDelivery.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "JWT_User")
public class JWTUserInfo {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String roles;

    public JWTUserInfo(String name, String email, String password, String roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
