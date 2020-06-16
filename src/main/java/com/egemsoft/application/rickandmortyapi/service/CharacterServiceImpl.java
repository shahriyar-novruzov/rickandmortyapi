package com.egemsoft.application.rickandmortyapi.service;

import com.egemsoft.application.rickandmortyapi.model.Character;
import com.egemsoft.application.rickandmortyapi.repository.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl extends AbstractServiceImpl<Character> implements CharacterService {

    private final CharacterRepository characterRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public List<Character> findCharacters(Integer pageNumber) {
        return super.findPaginatedData(characterRepository.getCharacters(), pageNumber);
    }

    @Override
    public Character findById(Long id) {
        return super.findById(characterRepository.getCharacters(), id);
    }
}
