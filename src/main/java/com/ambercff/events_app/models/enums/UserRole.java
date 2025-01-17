package com.ambercff.events_app.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum UserRole {
    ORGANIZADOR("organizador"),
    PARTICIPANTE("participante");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    // Verifica se a role inserida realmente existe, se existir ela é retornada, se não um erro é retornado
    @JsonCreator
    public static UserRole fromString(String value) {
        for (UserRole userRole : UserRole.values()){
            if (userRole.name().equalsIgnoreCase(value)) {
                return userRole;
            }
        }
        throw new IllegalArgumentException("Role inválida!");
    }
}
