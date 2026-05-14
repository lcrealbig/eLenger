package com.api.lenger.common.exception;

public record ErrorResponse(String errorId, String message, String version) {
}
