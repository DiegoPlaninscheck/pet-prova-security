package br.org.sesisenai.clinipet.utils;

import br.org.sesisenai.clinipet.model.entity.*;
import br.org.sesisenai.clinipet.model.enums.StatusAgenda;
import br.org.sesisenai.clinipet.model.enums.TipoAnimal;
import br.org.sesisenai.clinipet.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class BancoUtils {
    @Autowired
    private AgendaRepository agendaRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private AtendenteRepository atendenteRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProntuarioRepository prontuarioRepository;
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private VeterinarioRepository veterinarioRepository;


    @PostConstruct
    public void popularBanco() {

        Veterinario veterinario = new Veterinario();
        veterinario.setNome("Veterinário");
        veterinario.setEmail("veterinario@clinipet.com");
        veterinario.setSenha("clinipet");
        veterinario.setTelefone("99999-9999");
        veterinario.setEspecialidade("Dermatologista");
        veterinario = veterinarioRepository.save(veterinario);

        Atendente atendente = new Atendente();
        atendente.setNome("Atendente");
        atendente.setEmail("atendente@clinipet.com");
        atendente.setSenha("clinipet");
        atendente.setTelefone("99999-9999");
        atendente = atendenteRepository.save(atendente);

        Cliente cliente = new Cliente();
        cliente.setNome("Cliente");
        cliente.setEmail("cliente@gmail.com");
        cliente.setSenha("clinipet");
        cliente.setTelefone("99999-9999");
        cliente = clienteRepository.save(cliente);

        Animal animal = new Animal();
        animal.setNome("Rex");
        animal.setTipo(TipoAnimal.CACHORRO);
        animal.setDataNascimento(LocalDate.now());
        animal.setDono(cliente);
        animal = animalRepository.save(animal);

        Servico servico = new Servico();
        servico.setNome("Banho e Tosa");
        servico.setPreco(50.00);
        servico = servicoRepository.save(servico);

        Agenda agenda = new Agenda();
        agenda.setDataConsulta(LocalDate.now());
        agenda.setStatus(StatusAgenda.AGENDADO);
        agenda.setAnimal(animal);
        agenda.setServico(servico);
        agenda.setVeterinario(veterinario);
        agenda = agendaRepository.save(agenda);

        Prontuario prontuario = new Prontuario();
        prontuario.setAnimal(animal);
        prontuario.setAgenda(agenda);
        prontuario.setDataCriacao(LocalDateTime.now());
        prontuario.setDescricao("Animal com alergia");
        prontuario = prontuarioRepository.save(prontuario);
    }


}
