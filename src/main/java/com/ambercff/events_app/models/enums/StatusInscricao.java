package com.ambercff.events_app.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum StatusInscricao {

    CONFIRMADA("confirmada"),
    CANCELADA("cancelada");

    private String status;

    StatusInscricao(String status){
        this.status = status;
    }

    // Verifica se a role inserida realmente existe, se existir ela é retornada, se não um erro é retornado
    @JsonCreator
    public static StatusInscricao fromString(String value) {
        for (StatusInscricao statusInscricao : StatusInscricao.values()){
            if (statusInscricao.name().equalsIgnoreCase(value)) {
                return statusInscricao;
            }
        }
        throw new IllegalArgumentException("Status inválido!");
    }
}
