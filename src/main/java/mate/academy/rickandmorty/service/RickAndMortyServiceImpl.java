package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.exception.CharacterNotFoundException;
import mate.academy.rickandmorty.exception.EmptyLocalDatabaseException;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickAndMortyServiceImpl implements RickAndMortyService {

    private final CharacterRepository repository;
    private final SpecificationProvider specificationProvider;

    @Override
    public Character randomCharacterGenerated() {
        long recordCount = repository.count();

        if (recordCount == 0) {
            throw new EmptyLocalDatabaseException("Empty local database");
        }

        int randomIndex = new Random().nextInt((int)recordCount) + 1;
        return repository.findById((long) randomIndex)
                .orElseThrow(() -> new CharacterNotFoundException(
                        "Not found Character with id " + randomIndex));
    }

    @Override
    public List<Character> findByName(String name) {
        Specification<Character> specification = specificationProvider.getSpecification(name);
        return repository.findAll(specification);
    }
}
