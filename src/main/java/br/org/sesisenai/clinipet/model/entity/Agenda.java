package br.org.sesisenai.clinipet.model.entity;

import br.org.sesisenai.clinipet.model.enums.StatusAgenda;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataConsulta;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

    @Enumerated(EnumType.STRING)
    private StatusAgenda status;

    @OneToOne(mappedBy = "agenda")
    private Prontuario prontuario;

}
