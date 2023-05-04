package br.org.sesisenai.clinipet.repository;

import br.org.sesisenai.clinipet.model.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}