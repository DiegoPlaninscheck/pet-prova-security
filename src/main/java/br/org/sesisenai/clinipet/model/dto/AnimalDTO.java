package br.org.sesisenai.clinipet.model.dto;

import br.org.sesisenai.clinipet.model.entity.Animal;
import br.org.sesisenai.clinipet.model.entity.Cliente;
import br.org.sesisenai.clinipet.model.enums.TipoAnimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDTO {
    private String nome;
    private TipoAnimal tipo;
    private LocalDate dataNascimento;
    private Cliente dono;

    public Animal toEntity() {
        Animal animal = new Animal();
        BeanUtils.copyProperties(this, animal);
        return animal;
    }
}
