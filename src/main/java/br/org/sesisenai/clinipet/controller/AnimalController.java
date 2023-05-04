package br.org.sesisenai.clinipet.controller;

import br.org.sesisenai.clinipet.model.dto.AnimalDTO;
import br.org.sesisenai.clinipet.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/animal")
public class AnimalController implements InterfaceController<Long, AnimalDTO> {
    private final AnimalService animalService;

    @Override
    public ResponseEntity<?> salvar(AnimalDTO animalDTO) {
        return ResponseEntity.ok(animalService.salvar(animalDTO));
    }

    @Override
    public ResponseEntity<?> atualizar(Long id, AnimalDTO animalDTO) {
        return ResponseEntity.ok(animalService.atualizar(id, animalDTO));
    }

    @Override
    public ResponseEntity<?> buscarPorId(Long id) {
        return ResponseEntity.ok(animalService.buscarPorId(id));
    }

    @Override
    public ResponseEntity<?> excluirPorId(Long id) {
        animalService.excluirPorId(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> buscarTodos() {
        return ResponseEntity.ok(animalService.buscarTodos());
    }

}
