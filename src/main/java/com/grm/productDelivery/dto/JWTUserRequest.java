package com.grm.productDelivery.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class JWTUserRequest {
    private String name;
    private String email;
    private String password;
    private String roles;
}
