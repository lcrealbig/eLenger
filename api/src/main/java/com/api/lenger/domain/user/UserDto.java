package com.api.lenger.domain.user;


import java.io.Serializable;
import java.util.UUID;

public record UserDto(UUID id,
                      String email,
                      boolean enabled) implements Serializable {
}