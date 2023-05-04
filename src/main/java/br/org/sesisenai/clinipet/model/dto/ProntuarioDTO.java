package br.org.sesisenai.clinipet.model.dto;

import br.org.sesisenai.clinipet.model.entity.Agenda;
import br.org.sesisenai.clinipet.model.entity.Animal;
import br.org.sesisenai.clinipet.model.entity.Prontuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProntuarioDTO {
    private LocalDateTime dataCriacao;
    private String descricao;
    private Animal animal;
    private Agenda agenda;

    public Prontuario toEntity() {
        Prontuario prontuario = new Prontuario();
        BeanUtils.copyProperties(this, prontuario);
        return prontuario;
    }
}
