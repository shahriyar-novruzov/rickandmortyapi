package com.egemsoft.application.rickandmortyapi.client;

import com.egemsoft.application.rickandmortyapi.logger.ESLogger;
import com.egemsoft.application.rickandmortyapi.model.Character;
import com.egemsoft.application.rickandmortyapi.model.Episode;
import com.egemsoft.application.rickandmortyapi.model.Location;
import com.egemsoft.application.rickandmortyapi.model.RestResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RickAndMortyApiClientFallback implements RickAndMortyApiClient {

    private static final ESLogger logger = ESLogger.getLogger(RickAndMortyApiClientFallback.class);

    @Override
    public RestResponse<List<Episode>> getEpisodes(Integer pageNumber) {
        return null;
    }

    @Override
    public RestResponse<List<Character>> getCharacters(Integer pageNumber) {
        return null;
    }
}
