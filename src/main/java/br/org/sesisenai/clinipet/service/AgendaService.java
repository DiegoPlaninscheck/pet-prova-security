package br.org.sesisenai.clinipet.service;

import br.org.sesisenai.clinipet.model.dto.AgendaDTO;
import br.org.sesisenai.clinipet.model.entity.Agenda;
import br.org.sesisenai.clinipet.repository.AgendaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class AgendaService implements InterfaceService<Agenda,Long, AgendaDTO>{
    private AgendaRepository agendaRepository;

    @Override
    public Agenda salvar(AgendaDTO agendaDTO) {
        return agendaRepository.save(agendaDTO.toEntity());
    }

    @Override
    public Agenda atualizar(Long id, AgendaDTO agendaDTO) {
        Agenda agenda = agendaDTO.toEntity();
        agenda.setId(id);
        return agendaRepository.save(agenda);
    }

    @Override
    public Agenda buscarPorId(Long id) {
        return agendaRepository.findById(id).get();
    }

    @Override
    public void excluirPorId(Long id) {
        agendaRepository.deleteById(id);
    }

    @Override
    public Collection<Agenda> buscarTodos() {
        return agendaRepository.findAll();
    }
}
