package br.org.sesisenai.clinipet.service;

import java.util.Collection;

public interface InterfaceService<OBJ,ID,DTO> {
    OBJ salvar(DTO dto);
    OBJ atualizar(ID id, DTO dto);
    OBJ buscarPorId(ID id);
    void excluirPorId(ID id);
    Collection<OBJ> buscarTodos();
}
