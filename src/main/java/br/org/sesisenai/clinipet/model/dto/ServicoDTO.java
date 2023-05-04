package br.org.sesisenai.clinipet.model.dto;

import br.org.sesisenai.clinipet.model.entity.Servico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicoDTO {
    private String nome;
    private Double preco;

    public Servico toEntity() {
        Servico servico = new Servico();
        BeanUtils.copyProperties(this, servico);
        return servico;
    }
}
