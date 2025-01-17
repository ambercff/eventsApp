package com.ambercff.events_app.dtos.evento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

public record EventoCreateDTO(@NotBlank String titulo,
                              @NotBlank String descricao,
                              @NotNull LocalDateTime data,
                              @NotBlank String local,
                              @PositiveOrZero Integer capacidadeMaxima) {
}
