package br.org.sesisenai.clinipet.repository;

import br.org.sesisenai.clinipet.model.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

        Optional<Pessoa> findPessoaByEmail(String email);
}
