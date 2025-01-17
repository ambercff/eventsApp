package com.ambercff.events_app.services.inscricao;

import com.ambercff.events_app.dtos.inscricao.InscricaoCreateDTO;
import com.ambercff.events_app.models.Inscricao;

public interface InscricaoCreateService {
    Inscricao createInscription(InscricaoCreateDTO data);
}
