package com.egemsoft.application.rickandmortyapi.service;

import com.egemsoft.application.rickandmortyapi.model.Episode;
import com.egemsoft.application.rickandmortyapi.model.ResponseInfo;
import com.egemsoft.application.rickandmortyapi.model.RestResponse;
import com.egemsoft.application.rickandmortyapi.repository.EpisodeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeServiceImpl extends AbstractServiceImpl<Episode> implements EpisodeService {

    private final static String EPISODE_PAGE_URL = "/episode/?page=";

    private final EpisodeRepository episodeRepository;
    private final String url;

    public EpisodeServiceImpl(EpisodeRepository episodeRepository,
                              @Value("${client.rickandmortyapi.url}") String url) {
        this.episodeRepository = episodeRepository;
        this.url = url;
    }

    @Override
    public RestResponse<List<Episode>> findEpisodes(Integer pageNumber) {

        List<Episode> episodes = episodeRepository.getEpisodes();
        ResponseInfo responseInfo = getResponseInfo(episodes.size(), pageNumber, url.concat(EPISODE_PAGE_URL));
        List<Episode> paginatedEpisodes = super.findPaginatedData(episodes, pageNumber);

        return RestResponse.of(paginatedEpisodes, responseInfo);
    }

    @Override
    public Episode findById(Long id) {
        return super.findById(episodeRepository.getEpisodes(), id);
    }
}
