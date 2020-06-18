package com.egemsoft.application.rickandmortyapi.service.impl;

import com.egemsoft.application.rickandmortyapi.logger.ESLogger;
import com.egemsoft.application.rickandmortyapi.model.Base;
import com.egemsoft.application.rickandmortyapi.model.Character;
import com.egemsoft.application.rickandmortyapi.model.ResponseInfo;
import com.egemsoft.application.rickandmortyapi.model.RestResponse;
import com.egemsoft.application.rickandmortyapi.repository.CharacterRepository;
import com.egemsoft.application.rickandmortyapi.service.CharacterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * Service for operations of Characters
 *
 * @author Shahriyar Novruzov
 * since 1.0
 */
@Service
public class CharacterServiceImpl extends AbstractServiceImpl<Character> implements CharacterService {

    private static final ESLogger logger = ESLogger.getLogger(CharacterServiceImpl.class);
    private static final String SORT_BY_NAME = "name";
    private static final String SORT_BY_EPISODE = "episode";
    private static final String CHARACTER_PAGE_URL = "/character/?page=";

    private final CharacterRepository characterRepository;
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
    public RestResponse<List<Character>> findPaginated(Integer pageNumber, String sortBy) {
        logger.debug("findPaginated pageNumber: {}", pageNumber);
        List<Character> characters = characterRepository.getCharacters();
        characters.sort(getComparator(sortBy));
        ResponseInfo responseInfo = getResponseInfo(characters.size(), pageNumber, url.concat(CHARACTER_PAGE_URL));
        List<Character> paginatedCharacters = findPaginatedData(characters, pageNumber);

        return RestResponse.of(paginatedCharacters, responseInfo);
    }

    private Comparator<Character> getComparator(String sortBy) {
        if (sortBy == null)
            return Comparator.comparing(Base::getId);
        else if (sortBy.equals(SORT_BY_NAME))
            return Comparator.comparing(Character::getName);
        else if (sortBy.equals(SORT_BY_EPISODE))
            return Comparator.comparingInt(character -> character.getEpisode().size());
        else
            return Comparator.comparing(Base::getId);
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
