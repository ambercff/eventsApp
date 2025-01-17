package com.ambercff.events_app.models;

import com.ambercff.events_app.models.enums.StatusInscricao;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "inscricao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inscricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscricao")
    private Long idInscricao;

    @ManyToOne
    @JoinColumn(name = "id_participante", nullable = false)
    private User participante;

    @ManyToOne
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento evento;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_inscricao")
    private StatusInscricao statusInscricao;

}
