package br.org.sesisenai.clinipet.repository;

import br.org.sesisenai.clinipet.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}