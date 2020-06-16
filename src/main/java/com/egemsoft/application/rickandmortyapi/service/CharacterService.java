package com.egemsoft.application.rickandmortyapi.service;

import com.egemsoft.application.rickandmortyapi.model.Character;
import com.egemsoft.application.rickandmortyapi.model.RestResponse;

import java.util.List;

public interface CharacterService {
    RestResponse<List<Character>> findPaginated(Integer pageNumber);

    Character findById(Long id);
}
