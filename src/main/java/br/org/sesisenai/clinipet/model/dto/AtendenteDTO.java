package br.org.sesisenai.clinipet.model.dto;

import br.org.sesisenai.clinipet.model.entity.Atendente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtendenteDTO {
    private String nome;
    private String email;
    private String senha;
    private String telefone;

    public Atendente toEntity(){
        Atendente atendente = new Atendente();
        BeanUtils.copyProperties(this, atendente);
        return atendente;
    }
}
