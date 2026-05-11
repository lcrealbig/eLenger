// src/main/java/com/yourapp/dto/UserDto.java
package com.api.lenger.common;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private boolean enabled;
}