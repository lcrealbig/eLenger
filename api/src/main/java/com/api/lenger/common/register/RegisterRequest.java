package com.api.lenger.common.register;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest (
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}