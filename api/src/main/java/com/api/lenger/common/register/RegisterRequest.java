// src/main/java/com/yourapp/dto/RegisterRequest.java
package com.api.lenger.common.register;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
}