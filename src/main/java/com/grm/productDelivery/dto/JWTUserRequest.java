package com.grm.productDelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTUserRequest {
    private String name;
    private String email;
    private String password;
    private String roles;
}
