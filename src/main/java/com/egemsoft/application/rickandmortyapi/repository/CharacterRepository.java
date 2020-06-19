package com.egemsoft.application.rickandmortyapi.repository;

import com.egemsoft.application.rickandmortyapi.model.Character;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for store Characters in memory
 */
@Repository
public class CharacterRepository {

    private List<Character> characters = new ArrayList<>();

    /**
     * @param paginatedCharacters Characters on every page
     */
    public void addAll(List<Character> paginatedCharacters) {
        this.characters.addAll(paginatedCharacters);
    }

    /**
     * @return all Characters in store
     */
    public List<Character> getCharacters() {
        return this.characters;
    }
}
