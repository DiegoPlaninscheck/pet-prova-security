package br.org.sesisenai.clinipet.model.dto;

import br.org.sesisenai.clinipet.model.entity.Veterinario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeterinarioDTO {
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String especialidade;

    public Veterinario toEntity(){
        Veterinario veterinario = new Veterinario();
        BeanUtils.copyProperties(this, veterinario);
        return veterinario;
    }
}
