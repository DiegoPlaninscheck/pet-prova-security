package br.org.sesisenai.clinipet.model.dto;

import br.org.sesisenai.clinipet.model.entity.Animal;
import br.org.sesisenai.clinipet.model.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private String nome;
    private String email;
    private String senha;
    private String telefone;

    public Cliente toEntity(){
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(this, cliente);
        return cliente;
    }
}
