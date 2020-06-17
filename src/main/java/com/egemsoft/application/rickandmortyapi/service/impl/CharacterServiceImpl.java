package com.egemsoft.application.rickandmortyapi.service.impl;

import com.egemsoft.application.rickandmortyapi.logger.ESLogger;
import com.egemsoft.application.rickandmortyapi.model.Character;
import com.egemsoft.application.rickandmortyapi.model.ResponseInfo;
import com.egemsoft.application.rickandmortyapi.model.RestResponse;
import com.egemsoft.application.rickandmortyapi.repository.CharacterRepository;
import com.egemsoft.application.rickandmortyapi.service.CharacterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for operations of Characters
 *
 * @author Shahriyar Novruzov
 * since 1.0
 */
@Service
public class CharacterServiceImpl extends AbstractServiceImpl<Character> implements CharacterService {

    /**
     * Safe Egemsoft logger
     */
    private static final ESLogger logger = ESLogger.getLogger(CharacterServiceImpl.class);

    /**
     * Character api url
     */
    private final static String CHARACTER_PAGE_URL = "/character/?page=";

    /**
     * Data store for Characters
     */
    private final CharacterRepository characterRepository;

    /**
     * Root url for application
     */
    private final String url;

    /**
     * @param characterRepository injected Character repository for getting records
     * @param url                 root url for application
     */
    public CharacterServiceImpl(CharacterRepository characterRepository,
                                @Value("${ms.full.url}") String url) {
        this.characterRepository = characterRepository;
        this.url = url;
    }

    /**
     * @param pageNumber requested page
     * @return found Characters on requested page
     */
    @Override
    public RestResponse<List<Character>> findPaginated(Integer pageNumber) {
        logger.debug("findPaginated pageNumber: {}", pageNumber);
        List<Character> characters = characterRepository.getCharacters();
        ResponseInfo responseInfo = getResponseInfo(characters.size(), pageNumber, url.concat(CHARACTER_PAGE_URL));
        List<Character> paginatedCharacters = super.findPaginatedData(characters, pageNumber);

        return RestResponse.of(paginatedCharacters, responseInfo);
    }

    /**
     * @param id Character id
     * @return found Character
     */
    @Override
    public Character findById(Long id) {
        logger.debug("findById id: {}", id);
        return super.findById(characterRepository.getCharacters(), id);
    }
}
