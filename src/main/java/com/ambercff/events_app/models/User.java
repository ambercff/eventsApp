package com.ambercff.events_app.models;

import com.ambercff.events_app.dtos.user.UserCreateDTO;
import com.ambercff.events_app.models.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    private String nome;
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name="user_role")
    private UserRole userRole;

    private Boolean ativo;

    @ManyToMany(mappedBy = "organizadores")
    private Set<Evento> eventos;

}
