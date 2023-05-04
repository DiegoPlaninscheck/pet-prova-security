package br.org.sesisenai.clinipet.repository;

import br.org.sesisenai.clinipet.model.entity.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtendenteRepository extends JpaRepository<Atendente, Long> {
}