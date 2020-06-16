package com.egemsoft.application.rickandmortyapi.controller;

import com.egemsoft.application.rickandmortyapi.model.Character;
import com.egemsoft.application.rickandmortyapi.model.RestResponse;
import com.egemsoft.application.rickandmortyapi.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/character")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public RestResponse<List<Character>> findCharacters(@RequestParam(name = "page") @Nullable Integer pageNumber) {
        return RestResponse.of(characterService.findCharacters(pageNumber));
    }

    @GetMapping("/{id}")
    public Character findById(@PathVariable @NotNull Long id) {
        return characterService.findById(id);
    }
}
