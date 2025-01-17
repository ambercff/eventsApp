package com.ambercff.events_app.dtos.evento;

import com.ambercff.events_app.models.User;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record EventoDTO(Long idEvento,
                        String titulo,
                        String descricao,
                        LocalDateTime data,
                        String local,
                        Integer capacidadeMaxima,
                        Set<User> organizadores
                        ) {
}
