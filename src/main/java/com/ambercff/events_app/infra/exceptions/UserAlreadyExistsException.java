package com.ambercff.events_app.infra.exceptions;

import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends RuntimeException {
    private final String code;
    public UserAlreadyExistsException(String message) {
        super(message);
        this.code = "USER_ALREADY_EXISTS";
    }
}
