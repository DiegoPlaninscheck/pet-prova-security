package br.org.sesisenai.clinipet.model.dto;

import br.org.sesisenai.clinipet.model.entity.Agenda;
import br.org.sesisenai.clinipet.model.entity.Animal;
import br.org.sesisenai.clinipet.model.entity.Servico;
import br.org.sesisenai.clinipet.model.entity.Veterinario;
import br.org.sesisenai.clinipet.model.enums.StatusAgenda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaDTO {
    private LocalDate dataConsulta;
    private Animal animal;
    private Veterinario veterinario;
    private Servico servico;
    private StatusAgenda status;

    public Agenda toEntity() {
        Agenda agenda = new Agenda();
        BeanUtils.copyProperties(this, agenda);
        return agenda;
    }
}
