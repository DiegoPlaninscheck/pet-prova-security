package br.org.sesisenai.clinipet.service;

import br.org.sesisenai.clinipet.model.dto.ServicoDTO;
import br.org.sesisenai.clinipet.model.entity.Servico;
import br.org.sesisenai.clinipet.repository.ServicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class ServicoService implements InterfaceService<Servico,Long, ServicoDTO> {
    private final ServicoRepository servicoRepository;

    @Override
    public Servico salvar(ServicoDTO servicoDTO) {
        return servicoRepository.save(servicoDTO.toEntity());
    }

    @Override
    public Servico atualizar(Long id, ServicoDTO servicoDTO) {
        Servico servico = servicoDTO.toEntity();
        servico.setId(id);
        return servicoRepository.save(servico);
    }

    @Override
    public Servico buscarPorId(Long id) {
        return servicoRepository.findById(id).get();
    }

    @Override
    public void excluirPorId(Long id) {
        servicoRepository.deleteById(id);
    }

    @Override
    public Collection<Servico> buscarTodos() {
        return servicoRepository.findAll();
    }

}
