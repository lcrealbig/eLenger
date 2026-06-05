package com.api.lenger.domain.user;


import lombok.Builder;

import java.io.Serializable;
import java.util.UUID;

@Builder
public record UserDto(UUID id,
                      String email,
                      boolean enabled) implements Serializable {
}