package com.ambercff.events_app.infra.exceptions;

import lombok.Getter;

@Getter
public class EventNotFoundException extends RuntimeException {
    private final String code;

    public EventNotFoundException(String message){
        super(message);
        this.code = "EVENT_NOT_FOUND";
    }
}
