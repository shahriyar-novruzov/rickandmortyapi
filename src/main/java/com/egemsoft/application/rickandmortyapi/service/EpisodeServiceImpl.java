package com.egemsoft.application.rickandmortyapi.service;

import com.egemsoft.application.rickandmortyapi.model.Episode;
import com.egemsoft.application.rickandmortyapi.repository.EpisodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeServiceImpl extends AbstractServiceImpl<Episode> implements EpisodeService {

    private final EpisodeRepository episodeRepository;

    public EpisodeServiceImpl(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }

    @Override
    public List<Episode> findEpisodes(Integer pageNumber) {
        return super.findPaginatedData(episodeRepository.getEpisodes(), pageNumber);
    }

    @Override
    public Episode findById(Long id) {
        return super.findById(episodeRepository.getEpisodes(), id);
    }
}
