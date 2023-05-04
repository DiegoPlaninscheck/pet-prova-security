package br.org.sesisenai.clinipet.controller;

import br.org.sesisenai.clinipet.model.dto.ProntuarioDTO;
import br.org.sesisenai.clinipet.service.ProntuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prontuario")
@AllArgsConstructor
public class ProntuarioController implements InterfaceController<Long, ProntuarioDTO>{
    private final ProntuarioService prontuarioService;
    @Override
    public ResponseEntity<?> salvar(ProntuarioDTO prontuarioDTO) {
        return ResponseEntity.ok(prontuarioService.salvar(prontuarioDTO));
    }
    @Override
    public ResponseEntity<?> atualizar(Long id, ProntuarioDTO prontuarioDTO) {
        return ResponseEntity.ok(prontuarioService.atualizar(id, prontuarioDTO));
    }
    @Override
    public ResponseEntity<?> buscarPorId(Long id) {
        return ResponseEntity.ok(prontuarioService.buscarPorId(id));
    }
    @Override
    public ResponseEntity<?> excluirPorId(Long id) {
        prontuarioService.excluirPorId(id);
        return ResponseEntity.ok().build();
    }
    @Override
    public ResponseEntity<?> buscarTodos() {
        return ResponseEntity.ok(prontuarioService.buscarTodos());
    }
}
