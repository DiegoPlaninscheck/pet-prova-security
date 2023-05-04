package br.org.sesisenai.clinipet.repository;

import br.org.sesisenai.clinipet.model.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}