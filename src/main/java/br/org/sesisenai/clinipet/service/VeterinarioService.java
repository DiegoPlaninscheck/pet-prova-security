package br.org.sesisenai.clinipet.service;

import br.org.sesisenai.clinipet.model.dto.VeterinarioDTO;
import br.org.sesisenai.clinipet.model.entity.Veterinario;
import br.org.sesisenai.clinipet.repository.VeterinarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class VeterinarioService implements InterfaceService<Veterinario,Long, VeterinarioDTO> {
    private final VeterinarioRepository veterinarioRepository;
    @Override
    public Veterinario salvar(VeterinarioDTO veterinarioDTO) {
        return veterinarioRepository.save(veterinarioDTO.toEntity());
    }
    @Override
    public Veterinario atualizar(Long id, VeterinarioDTO veterinarioDTO) {
        Veterinario veterinario = veterinarioDTO.toEntity();
        veterinario.setId(id);
        return veterinarioRepository.save(veterinario);
    }
    @Override
    public Veterinario buscarPorId(Long id) {
        return veterinarioRepository.findById(id).get();
    }
    @Override
    public void excluirPorId(Long id) {
        veterinarioRepository.deleteById(id);
    }
    @Override
    public Collection<Veterinario> buscarTodos() {
        return veterinarioRepository.findAll();
    }

}
