package br.org.sesisenai.clinipet.controller;

import br.org.sesisenai.clinipet.model.dto.AgendaDTO;
import br.org.sesisenai.clinipet.service.AgendaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agenda")
@AllArgsConstructor
public class AgendaController implements InterfaceController<Long, AgendaDTO>{
    private final AgendaService agendaService;
    @Override
    public ResponseEntity<?> salvar(AgendaDTO agendaDTO) {
        return ResponseEntity.ok(agendaService.salvar(agendaDTO));
    }
    @Override
    public ResponseEntity<?> atualizar(Long id, AgendaDTO agendaDTO) {
        return ResponseEntity.ok(agendaService.atualizar(id, agendaDTO));
    }
    @Override
    public ResponseEntity<?> buscarPorId(Long id) {
        return ResponseEntity.ok(agendaService.buscarPorId(id));
    }
    @Override
    public ResponseEntity<?> excluirPorId(Long id) {
        agendaService.excluirPorId(id);
        return ResponseEntity.ok().build();
    }
    @Override
    public ResponseEntity<?> buscarTodos() {
        return ResponseEntity.ok(agendaService.buscarTodos());
    }
}
