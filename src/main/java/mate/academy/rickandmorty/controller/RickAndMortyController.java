package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.RickAndMortyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rickandmorty")
@RequiredArgsConstructor
public class RickAndMortyController {

    private final RickAndMortyService rickAndMortyService;

    @GetMapping("/random")
    public Character getRandomCharacter() {
        return rickAndMortyService.randomCharacterGenerated();
    }

    @GetMapping("/{name}")
    public List<Character> getByName(@PathVariable String name) {
        return rickAndMortyService.findByName(name);
    }
}
