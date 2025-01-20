package com.ambercff.events_app.dtos.inscricao;

import com.ambercff.events_app.models.Evento;
import com.ambercff.events_app.models.User;
import com.ambercff.events_app.models.enums.StatusInscricao;
import lombok.Builder;

@Builder
public record InscricaoDTO(Long idInscricao,
                           User participante,
                           Evento evento,
                           StatusInscricao statusInscricao
                           ) {
}
