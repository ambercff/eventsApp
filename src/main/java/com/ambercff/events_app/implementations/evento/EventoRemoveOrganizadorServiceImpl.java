package com.ambercff.events_app.implementations.evento;

import com.ambercff.events_app.infra.exceptions.EventNotFoundException;
import com.ambercff.events_app.infra.exceptions.UserNotFoundException;
import com.ambercff.events_app.models.Evento;
import com.ambercff.events_app.models.User;
import com.ambercff.events_app.models.enums.UserRole;
import com.ambercff.events_app.repositories.EventoRepository;
import com.ambercff.events_app.repositories.UserRepository;
import com.ambercff.events_app.services.evento.EventoRemoveOrganizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventoRemoveOrganizadorServiceImpl implements EventoRemoveOrganizadorService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public String removeOrganizador(String email, String titulo) {
        User user = (User) userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado!"));

        if (user.getUserRole() != UserRole.ORGANIZADOR) {
            return "Usuário não é organizador!";
        }

        Evento evento = eventoRepository.findByTitulo(titulo);

        if(evento == null){
            throw new EventNotFoundException("Evento não encontrado!");
        }

        Set<User> organizadores = evento.getOrganizadores();

        if(organizadores.contains(user)){
            organizadores.remove(user);
            eventoRepository.save(evento);
            return "Organizador removido com sucesso!";
        } else {
            throw new UserNotFoundException("Esse evento não contém esse organizador!");
        }
    }
}
