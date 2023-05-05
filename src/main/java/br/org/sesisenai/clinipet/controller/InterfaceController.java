package br.org.sesisenai.clinipet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface InterfaceController<ID, DTO> {
    @PostMapping
    ResponseEntity<?> salvar(@RequestBody DTO dto);

    @PutMapping("/{id}")
    ResponseEntity<?> atualizar(@RequestBody ID id, DTO dto);

    @GetMapping("/{id}")
    ResponseEntity<?> buscarPorId(ID id);

    @DeleteMapping("/{id}")
    ResponseEntity<?> excluirPorId(ID id);

    @GetMapping
    ResponseEntity<?> buscarTodos();
}
