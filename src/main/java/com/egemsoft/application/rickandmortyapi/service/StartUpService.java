package com.egemsoft.application.rickandmortyapi.service;

import com.egemsoft.application.rickandmortyapi.client.RickAndMortyApiClient;
import com.egemsoft.application.rickandmortyapi.logger.ESLogger;
import com.egemsoft.application.rickandmortyapi.model.Character;
import com.egemsoft.application.rickandmortyapi.model.Episode;
import com.egemsoft.application.rickandmortyapi.model.Location;
import com.egemsoft.application.rickandmortyapi.model.RestResponse;
import com.egemsoft.application.rickandmortyapi.repository.CharacterRepository;
import com.egemsoft.application.rickandmortyapi.repository.EpisodeRepository;
import com.egemsoft.application.rickandmortyapi.repository.LocationRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class StartUpService {

    private static final ESLogger logger = ESLogger.getLogger(StartUpService.class);

    private final RickAndMortyApiClient rickAndMortyApiClient;
    private final CharacterRepository characterRepository;
    private final EpisodeRepository episodeRepository;
    private final LocationRepository locationRepository;

    public StartUpService(RickAndMortyApiClient rickAndMortyApiClient,
                          CharacterRepository characterRepository,
                          EpisodeRepository episodeRepository,
                          LocationRepository locationRepository) {
        this.rickAndMortyApiClient = rickAndMortyApiClient;
        this.characterRepository = characterRepository;
        this.episodeRepository = episodeRepository;
        this.locationRepository = locationRepository;
    }

    @PostConstruct
    public void init() {
        loadCharacters();
        loadEpisodes();
        loadLocations();
    }

    private void loadCharacters() {
        logger.info("Loading Characters started...");
        boolean hasNext = true;
        int pageNumber = 1;
        while (hasNext) {
            RestResponse<List<Character>> restResponse = rickAndMortyApiClient.getCharacters(pageNumber++);
            characterRepository.addAll(restResponse.getResults());
            hasNext = restResponse.getInfo().getNext() != null;
        }
        logger.info("Loading Characters finished successfully.");
    }

    private void loadEpisodes() {
        logger.info("Loading Episodes started...");
        boolean hasNext = true;
        int pageNumber = 1;
        while (hasNext) {
            RestResponse<List<Episode>> restResponse = rickAndMortyApiClient.getEpisodes(pageNumber++);
            episodeRepository.addAll(restResponse.getResults());
            hasNext = restResponse.getInfo().getNext() != null;
        }
        logger.info("Loading Episodes finished successfully.");
    }

    private void loadLocations() {
        logger.info("Loading Locations started...");
        boolean hasNext = true;
        int pageNumber = 1;
        while (hasNext) {
            RestResponse<List<Location>> restResponse = rickAndMortyApiClient.getLocations(pageNumber++);
            locationRepository.addAll(restResponse.getResults());
            hasNext = restResponse.getInfo().getNext() != null;
        }
        logger.info("Loading Locations finished successfully.");
    }
}
