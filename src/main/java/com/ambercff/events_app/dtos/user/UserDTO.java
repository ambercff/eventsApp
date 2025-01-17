package com.ambercff.events_app.dtos.user;

import com.ambercff.events_app.models.Evento;
import com.ambercff.events_app.models.enums.UserRole;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserDTO(Long idUser,
                      String nome,
                      String email,
                      String senha,
                      UserRole userRole,
                      Boolean ativo,
                      Set<Evento> eventos
                      ) {
}
