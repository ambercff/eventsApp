package com.ambercff.events_app.implementations.evento;

import com.ambercff.events_app.infra.exceptions.EventNotFoundException;
import com.ambercff.events_app.infra.exceptions.UserNotFoundException;
import com.ambercff.events_app.models.Evento;
import com.ambercff.events_app.models.User;
import com.ambercff.events_app.models.enums.UserRole;
import com.ambercff.events_app.repositories.EventoRepository;
import com.ambercff.events_app.repositories.UserRepository;
import com.ambercff.events_app.services.evento.EventoSetOrganizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoSetOrganizadorServiceImpl implements EventoSetOrganizadorService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public String setOrganizadorService(String email, String titulo) {
        User user = (User) userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado!"));

        if (!user.getAtivo()) {
            throw new UserNotFoundException("Usuário desativado!");
        }

        if (user.getUserRole() != UserRole.ORGANIZADOR) {
            return "Usuário não é organizador!";
        }

        Evento evento = eventoRepository.findByTitulo(titulo);
        if (evento == null) {
            throw new EventNotFoundException("Evento não encontrado!");
        }

        if (evento.getOrganizadores().contains(user)) {
            return "Usuário já está na lista de organizadores!";
        }

        evento.getOrganizadores().add(user);
        eventoRepository.save(evento);

        return "Organizador associado ao evento com sucesso!";
    }
}
