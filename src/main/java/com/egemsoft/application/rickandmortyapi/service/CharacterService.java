package com.egemsoft.application.rickandmortyapi.service;

import com.egemsoft.application.rickandmortyapi.model.Character;

import java.util.List;

public interface CharacterService {
    List<Character> findCharacters(Integer pageNumber);

    Character findById(Long id);
}
