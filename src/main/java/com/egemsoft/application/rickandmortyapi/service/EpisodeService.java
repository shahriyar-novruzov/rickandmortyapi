package com.egemsoft.application.rickandmortyapi.service;

import com.egemsoft.application.rickandmortyapi.model.Episode;
import com.egemsoft.application.rickandmortyapi.model.RestResponse;

import java.util.List;

public interface EpisodeService {
    RestResponse<List<Episode>> findEpisodes(Integer pageNumber);

    Episode findById(Long id);
}
