package com.ambercff.events_app.services.inscricao;

import com.ambercff.events_app.models.Inscricao;

import java.util.List;

public interface InscricaoGetAllByUserService {
    List<Inscricao> getAllInscriptionsByUser(String email);
}
