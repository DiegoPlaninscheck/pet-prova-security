package br.org.sesisenai.clinipet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface InterfaceController<ID, DTO> {
    @PostMapping
    ResponseEntity<?> salvar(@RequestBody DTO dto);

    @PutMapping("/{id}")
    ResponseEntity<?> atualizar(@PathVariable(name = "id") ID id,@RequestBody DTO dto);

    @GetMapping("/{id}")
    ResponseEntity<?> buscarPorId(@PathVariable(name = "id") ID id);

    @DeleteMapping("/{id}")
    ResponseEntity<?> excluirPorId(@PathVariable(name = "id") ID id);

    @GetMapping
    ResponseEntity<?> buscarTodos();
}
