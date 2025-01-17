package com.ambercff.events_app.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "evento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Long idEvento;

    private String titulo;
    private String descricao;
    private LocalDateTime data;
    private String local;

    @Column(name = "capacidade_maxima")
    private Integer capacidadeMaxima;

    @ManyToMany
    @JoinTable(
            name = "evento_organizadores",
            joinColumns = @JoinColumn(name = "id_evento"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    private Set<User> organizadores;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Inscricao> inscricoes;
}

