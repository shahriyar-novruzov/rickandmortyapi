package com.egemsoft.application.rickandmortyapi.service.impl;

import com.egemsoft.application.rickandmortyapi.model.RickAndMortyApi;
import com.egemsoft.application.rickandmortyapi.service.RickAndMortyApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RickAndMortyApiServiceImpl implements RickAndMortyApiService {

    private final static String CHARACTER_URL = "/character";
    private final static String EPISODE_URL = "/episode";
    private final static String LOCATION_URL = "/location";

    private final String url;

    public RickAndMortyApiServiceImpl(@Value("${ms.full.url}") String url) {
        this.url = url;
    }

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
