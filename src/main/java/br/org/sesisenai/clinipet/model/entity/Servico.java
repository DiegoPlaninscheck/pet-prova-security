package br.org.sesisenai.clinipet.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Double preco;
}