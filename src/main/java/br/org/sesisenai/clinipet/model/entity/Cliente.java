package br.org.sesisenai.clinipet.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Cliente extends Pessoa {
    @OneToMany(mappedBy = "dono")
    @JsonIgnore
    private List<Animal> animais;
}
