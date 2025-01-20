package com.ambercff.events_app.infra.exceptions;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
  private final String code;

  public UserNotFoundException(String message) {
        super(message);
        this.code = "USER_NOT_FOUND";
    }
}
