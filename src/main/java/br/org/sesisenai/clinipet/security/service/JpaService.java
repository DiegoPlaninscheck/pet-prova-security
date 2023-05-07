package br.org.sesisenai.clinipet.security.service;

import br.org.sesisenai.clinipet.model.entity.Pessoa;
import br.org.sesisenai.clinipet.repository.PessoaRepository;
import br.org.sesisenai.clinipet.security.model.entity.PessoaJpa;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class JpaService implements UserDetailsService {

    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Username loadUserByUsername: " + username);

        Optional<Pessoa> pessoa = pessoaRepository.findPessoaByEmail(username);

        System.out.println("Pessoa pega da repository: " + pessoa.toString());

        if(pessoa.isPresent()){
            System.out.println("Pessoa is present");
            return new PessoaJpa(pessoa.get());
        }

        throw new RuntimeException("Pessoa não encontrada!");
    }
}
