package com.egemsoft.application.rickandmortyapi.service;

import com.egemsoft.application.rickandmortyapi.model.Character;
import com.egemsoft.application.rickandmortyapi.model.ResponseInfo;
import com.egemsoft.application.rickandmortyapi.model.RestResponse;
import com.egemsoft.application.rickandmortyapi.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl extends AbstractServiceImpl<Character> implements CharacterService {

    private final static String CHARACTER_PAGE_URL = "/character/?page=";

    private final CharacterRepository characterRepository;
    private final String url;

    public CharacterServiceImpl(CharacterRepository characterRepository,
                                @Value("${client.rickandmortyapi.url}") String url) {
        this.characterRepository = characterRepository;
        this.url = url;
    }

    @Override
    public RestResponse<List<Character>> findCharacters(Integer pageNumber) {

        List<Character> characters = characterRepository.getCharacters();
        ResponseInfo responseInfo = getResponseInfo(characters.size(), pageNumber, url.concat(CHARACTER_PAGE_URL));
        List<Character> paginatedCharacters = super.findPaginatedData(characters, pageNumber);

        return RestResponse.of(paginatedCharacters, responseInfo);
    }

    @Override
    public Character findById(Long id) {
        return super.findById(characterRepository.getCharacters(), id);
    }
}
