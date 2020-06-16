package com.egemsoft.application.rickandmortyapi.client;

import com.egemsoft.application.rickandmortyapi.exception.ClientException;
import com.egemsoft.application.rickandmortyapi.logger.ESLogger;
import com.egemsoft.application.rickandmortyapi.model.Character;
import com.egemsoft.application.rickandmortyapi.model.Episode;
import com.egemsoft.application.rickandmortyapi.model.Location;
import com.egemsoft.application.rickandmortyapi.model.RestResponse;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RickAndMortyApiClientFallbackFactory implements FallbackFactory<RickAndMortyApiClient> {

    private static final ESLogger logger = ESLogger.getLogger(RickAndMortyApiClientFallbackFactory.class);

    @Override
    public RickAndMortyApiClient create(Throwable throwable) {
        return new RickAndMortyApiClient() {
            @Override
            public RestResponse<List<Episode>> getEpisodes(Integer pageNumber) {
                logger.error("getEpisodes.error cause: {}", throwable.getMessage());
                throw new ClientException(throwable.getMessage());
            }

            @Override
            public RestResponse<List<Character>> getCharacters(Integer pageNumber) {
                logger.error("getCharacters.error cause: {}", throwable.getMessage());
                throw new ClientException(throwable.getMessage());
            }

            @Override
            public RestResponse<List<Location>> getLocations(Integer pageNumber) {
                logger.error("getLocations.error cause: {}", throwable.getMessage());
                throw new ClientException(throwable.getMessage());
            }
        };
    }
}
