package br.org.sesisenai.clinipet.service;

import br.org.sesisenai.clinipet.model.dto.AnimalDTO;
import br.org.sesisenai.clinipet.model.entity.Animal;
import br.org.sesisenai.clinipet.repository.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
@AllArgsConstructor
public class AnimalService implements InterfaceService<Animal, Long, AnimalDTO>{
    private final AnimalRepository animalRepository;

    @Override
    public Animal salvar(AnimalDTO animalDTO) {
        return animalRepository.save(animalDTO.toEntity());
    }

    @Override
    public Animal atualizar(Long id, AnimalDTO animalDTO) {
        Animal animal = animalDTO.toEntity();
        animal.setId(id);
        return animalRepository.save(animal);
    }

    @Override
    public Animal buscarPorId(Long id) {
        return animalRepository.findById(id).get();
    }

    @Override
    public void excluirPorId(Long id) {
        animalRepository.deleteById(id);
    }

    @Override
    public Collection<Animal> buscarTodos() {
        return animalRepository.findAll();
    }
}
