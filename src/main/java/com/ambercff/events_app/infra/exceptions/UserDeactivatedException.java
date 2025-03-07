package com.ambercff.events_app.infra.exceptions;

import lombok.Getter;

@Getter
public class UserDeactivatedException extends RuntimeException {
    private final String code;

    public UserDeactivatedException(String message) {
        super(message);
        this.code = "USER_DEACTIVATED";
    }
}
