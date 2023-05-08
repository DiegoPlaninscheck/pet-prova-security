package br.org.sesisenai.clinipet.model.entity;

import br.org.sesisenai.clinipet.model.enums.TipoAnimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoAnimal tipo;

    private LocalDate dataNascimento;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cliente_id")
    private Cliente dono;
}