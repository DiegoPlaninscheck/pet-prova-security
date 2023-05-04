package br.org.sesisenai.clinipet.controller;

import br.org.sesisenai.clinipet.model.dto.ServicoDTO;
import br.org.sesisenai.clinipet.service.ServicoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servico")
@AllArgsConstructor
public class ServicoController implements InterfaceController<Long, ServicoDTO>{
    private final ServicoService servicoService;
    @Override
    public ResponseEntity<?> salvar(ServicoDTO servicoDTO) {
        return ResponseEntity.ok(servicoService.salvar(servicoDTO));
    }
    @Override
    public ResponseEntity<?> atualizar(Long id, ServicoDTO servicoDTO) {
        return ResponseEntity.ok(servicoService.atualizar(id, servicoDTO));
    }
    @Override
    public ResponseEntity<?> buscarPorId(Long id) {
        return ResponseEntity.ok(servicoService.buscarPorId(id));
    }
    @Override
    public ResponseEntity<?> excluirPorId(Long id) {
        servicoService.excluirPorId(id);
        return ResponseEntity.ok().build();
    }
    @Override
    public ResponseEntity<?> buscarTodos() {
        return ResponseEntity.ok(servicoService.buscarTodos());
    }
}
