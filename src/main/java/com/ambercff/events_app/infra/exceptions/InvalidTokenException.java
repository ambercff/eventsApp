package com.ambercff.events_app.infra.exceptions;

import lombok.Getter;

@Getter
public class InvalidTokenException extends RuntimeException {
    private final String code;

    public InvalidTokenException(String message) {
        super(message);
        this.code = "INVALID_TOKEN";
    }
}
