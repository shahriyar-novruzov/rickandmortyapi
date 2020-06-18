package com.egemsoft.application.rickandmortyapi.service;

import com.egemsoft.application.rickandmortyapi.model.Episode;
import com.egemsoft.application.rickandmortyapi.model.RestResponse;

import java.util.List;

public interface EpisodeService {
    RestResponse<List<Episode>> findPaginated(Integer pageNumber, String sortBy);

    Episode findById(Long id);
}
