package com.egemsoft.application.rickandmortyapi.repository;

import com.egemsoft.application.rickandmortyapi.model.Character;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CharacterRepository {

    private List<Character> characters = new ArrayList<>();

    public void addAll(List<Character> data) {
        this.characters.addAll(data);
    }

    public List<Character> getCharacters() {
        return this.characters;
    }
}
