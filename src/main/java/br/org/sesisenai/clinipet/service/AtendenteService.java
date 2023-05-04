package br.org.sesisenai.clinipet.service;

import br.org.sesisenai.clinipet.model.dto.AtendenteDTO;
import br.org.sesisenai.clinipet.model.entity.Atendente;
import br.org.sesisenai.clinipet.repository.AtendenteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class AtendenteService implements InterfaceService<Atendente,Long, AtendenteDTO> {
    private final AtendenteRepository atendenteRepository;
    @Override
    public Atendente salvar(AtendenteDTO atendenteDTO) {
        return atendenteRepository.save(atendenteDTO.toEntity());
    }
    @Override
    public Atendente atualizar(Long id, AtendenteDTO atendenteDTO) {
        Atendente atendente = atendenteDTO.toEntity();
        atendente.setId(id);
        return atendenteRepository.save(atendente);
    }

    @Override
    public Atendente buscarPorId(Long id) {
        return atendenteRepository.findById(id).get();
    }
    @Override
    public void excluirPorId(Long id) {
        atendenteRepository.deleteById(id);
    }
    @Override
    public Collection<Atendente> buscarTodos() {
        return atendenteRepository.findAll();
    }

}
