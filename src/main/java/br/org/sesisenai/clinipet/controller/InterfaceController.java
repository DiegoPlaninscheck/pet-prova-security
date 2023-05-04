package br.org.sesisenai.clinipet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface InterfaceController<ID, DTO> {
    @PostMapping
    ResponseEntity<?> salvar(DTO dto);

    @PutMapping("/{id}")
    ResponseEntity<?> atualizar(ID id, DTO dto);

    @GetMapping("/{id}")
    ResponseEntity<?> buscarPorId(ID id);

    @DeleteMapping("/{id}")
    ResponseEntity<?> excluirPorId(ID id);

    @GetMapping
    ResponseEntity<?> buscarTodos();
}
