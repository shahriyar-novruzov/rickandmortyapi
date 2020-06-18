package com.egemsoft.application.rickandmortyapi.service.impl;

import com.egemsoft.application.rickandmortyapi.model.RickAndMortyApi;
import com.egemsoft.application.rickandmortyapi.service.RickAndMortyApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service for getting all rickandmortyapi apis
 *
 * @author Shahriyar Novruzov
 * since 1.0
 */
@Service
public class RickAndMortyApiServiceImpl implements RickAndMortyApiService {

    private final static String CHARACTER_URL = "/character";
    private final static String EPISODE_URL = "/episode";
    private final static String LOCATION_URL = "/location";

    private final String url;

    /**
     * @param url full url for application
     */
    public RickAndMortyApiServiceImpl(@Value("${ms.full.url}") String url) {
        this.url = url;
    }

    /**
     * @return api urls for Characters, Episodes and Locations
     */
    @Override
    public RickAndMortyApi getRickAndMortyApi() {
        return RickAndMortyApi
                .builder()
                .characters(url.concat(CHARACTER_URL))
                .episodes(url.concat(EPISODE_URL))
                .locations(url.concat(LOCATION_URL))
                .build();
    }
}
