package br.org.sesisenai.clinipet.controller;

import br.org.sesisenai.clinipet.model.dto.ClienteDTO;
import br.org.sesisenai.clinipet.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
@AllArgsConstructor
public class ClienteController implements InterfaceController<Long, ClienteDTO> {
    private final ClienteService clienteService;
    @Override
    public ResponseEntity<?> salvar(ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.salvar(clienteDTO));
    }
    @Override
    public ResponseEntity<?> atualizar(Long id, ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.atualizar(id, clienteDTO));
    }
    @Override
    public ResponseEntity<?> buscarPorId(Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }
    @Override
    public ResponseEntity<?> excluirPorId(Long id) {
        clienteService.excluirPorId(id);
        return ResponseEntity.ok().build();
    }
    @Override
    public ResponseEntity<?> buscarTodos() {
        return ResponseEntity.ok(clienteService.buscarTodos());
    }
}
