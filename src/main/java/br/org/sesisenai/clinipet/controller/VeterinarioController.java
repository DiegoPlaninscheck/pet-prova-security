package br.org.sesisenai.clinipet.controller;

import br.org.sesisenai.clinipet.model.dto.VeterinarioDTO;
import br.org.sesisenai.clinipet.service.VeterinarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/veterinario")
public class VeterinarioController implements InterfaceController<Long, VeterinarioDTO>{
    private final VeterinarioService veterinarioService;
    @Override
    public ResponseEntity<?> salvar(VeterinarioDTO veterinarioDTO) {
        return ResponseEntity.ok(veterinarioService.salvar(veterinarioDTO));
    }
    @Override
    public ResponseEntity<?> atualizar(Long id, VeterinarioDTO veterinarioDTO) {
        return ResponseEntity.ok(veterinarioService.atualizar(id, veterinarioDTO));
    }
    @Override
    public ResponseEntity<?> buscarPorId(Long id) {
        return ResponseEntity.ok(veterinarioService.buscarPorId(id));
    }
    @Override
    public ResponseEntity<?> excluirPorId(Long id) {
        veterinarioService.excluirPorId(id);
        return ResponseEntity.ok().build();
    }
    @Override
    public ResponseEntity<?> buscarTodos() {
        return ResponseEntity.ok(veterinarioService.buscarTodos());
    }
}
