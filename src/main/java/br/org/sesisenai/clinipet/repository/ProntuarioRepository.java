package br.org.sesisenai.clinipet.repository;

import br.org.sesisenai.clinipet.model.entity.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
}