package com.egemsoft.application.rickandmortyapi.service;

import com.egemsoft.application.rickandmortyapi.model.Episode;

import java.util.List;

public interface EpisodeService {
    List<Episode> findEpisodes(Integer pageNumber);

    Episode findById(Long id);
}
