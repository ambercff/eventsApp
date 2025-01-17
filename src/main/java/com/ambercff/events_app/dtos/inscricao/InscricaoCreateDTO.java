package com.ambercff.events_app.dtos.inscricao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InscricaoCreateDTO(@NotBlank String email,
                                 @NotNull Long idEvento
                                 ) {
}
