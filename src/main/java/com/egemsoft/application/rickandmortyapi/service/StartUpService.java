package com.egemsoft.application.rickandmortyapi.service;

import com.egemsoft.application.rickandmortyapi.client.RickAndMortyApiClient;
import com.egemsoft.application.rickandmortyapi.model.Character;
import com.egemsoft.application.rickandmortyapi.model.Episode;
import com.egemsoft.application.rickandmortyapi.model.RestResponse;
import com.egemsoft.application.rickandmortyapi.repository.CharacterRepository;
import com.egemsoft.application.rickandmortyapi.repository.EpisodeRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class StartUpService {

    private final RickAndMortyApiClient rickAndMortyApiClient;
    private final CharacterRepository characterRepository;
    private final EpisodeRepository episodeRepository;

    public StartUpService(RickAndMortyApiClient rickAndMortyApiClient,
                          CharacterRepository characterRepository,
                          EpisodeRepository episodeRepository) {
        this.rickAndMortyApiClient = rickAndMortyApiClient;
        this.characterRepository = characterRepository;
        this.episodeRepository = episodeRepository;
    }

    @PostConstruct
    public void init() {
        loadCharacters();
        loadEpisodes();
    }

    private void loadCharacters() {
        boolean hasNext = true;
        int pageNumber = 1;
        while (hasNext) {
            RestResponse<List<Character>> restResponse = rickAndMortyApiClient.getCharacters(pageNumber++);
            characterRepository.addAll(restResponse.getResults());
            hasNext = restResponse.getInfo().getNext() != null;
        }
    }

    private void loadEpisodes() {
        boolean hasNext = true;
        int pageNumber = 1;
        while (hasNext) {
            RestResponse<List<Episode>> restResponse = rickAndMortyApiClient.getEpisodes(pageNumber++);
            episodeRepository.addAll(restResponse.getResults());
            hasNext = restResponse.getInfo().getNext() != null;
        }
    }
}
