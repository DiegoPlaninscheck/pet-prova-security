package br.org.sesisenai.clinipet.repository;

import br.org.sesisenai.clinipet.model.entity.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
}