package br.org.sesisenai.clinipet.service;

import br.org.sesisenai.clinipet.model.dto.ProntuarioDTO;
import br.org.sesisenai.clinipet.model.entity.Prontuario;
import br.org.sesisenai.clinipet.repository.ProntuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class ProntuarioService implements InterfaceService<Prontuario,Long, ProntuarioDTO> {
    private final ProntuarioRepository prontuarioRepository;
    @Override
    public Prontuario salvar(ProntuarioDTO prontuarioDTO) {
        return prontuarioRepository.save(prontuarioDTO.toEntity());
    }
    @Override
    public Prontuario atualizar(Long id, ProntuarioDTO prontuarioDTO) {
        Prontuario prontuario = prontuarioDTO.toEntity();
        prontuario.setId(id);
        return prontuarioRepository.save(prontuario);
    }

    @Override
    public Prontuario buscarPorId(Long id) {
        return prontuarioRepository.findById(id).get();
    }

    @Override
    public void excluirPorId(Long id) {
        prontuarioRepository.deleteById(id);
    }

    @Override
    public Collection<Prontuario> buscarTodos() {
        return prontuarioRepository.findAll();
    }
}
