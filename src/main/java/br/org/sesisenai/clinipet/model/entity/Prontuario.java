package br.org.sesisenai.clinipet.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Prontuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataCriacao;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @OneToOne
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;
}