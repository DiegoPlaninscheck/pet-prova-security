package br.org.sesisenai.clinipet.repository;

import br.org.sesisenai.clinipet.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}