// src/main/java/com/yourapp/dto/LoginRequest.java
package com.api.lenger.common.login;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}