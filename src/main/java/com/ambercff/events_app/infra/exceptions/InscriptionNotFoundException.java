package com.ambercff.events_app.infra.exceptions;

import lombok.Getter;

@Getter
public class InscriptionNotFoundException extends RuntimeException {
    private final String code;

    public InscriptionNotFoundException(String message) {
        super(message);
        this.code = "INSCRIPTION_NOT_FOUND";
    }
}
