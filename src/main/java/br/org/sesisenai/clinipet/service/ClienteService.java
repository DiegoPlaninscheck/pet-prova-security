package br.org.sesisenai.clinipet.service;

import br.org.sesisenai.clinipet.model.dto.ClienteDTO;
import br.org.sesisenai.clinipet.model.entity.Cliente;
import br.org.sesisenai.clinipet.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class ClienteService implements InterfaceService<Cliente,Long, ClienteDTO>{
    private final ClienteRepository clienteRepository;

    @Override
    public Cliente salvar(ClienteDTO clienteDTO) {
        return clienteRepository.save(clienteDTO.toEntity());
    }
    @Override
    public Cliente atualizar(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteDTO.toEntity();
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }
    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).get();
    }
    @Override
    public void excluirPorId(Long id) {
        clienteRepository.deleteById(id);
    }
    @Override
    public Collection<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

}
