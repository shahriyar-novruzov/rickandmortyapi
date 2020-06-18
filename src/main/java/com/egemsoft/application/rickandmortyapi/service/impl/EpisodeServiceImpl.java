package com.egemsoft.application.rickandmortyapi.service.impl;

import com.egemsoft.application.rickandmortyapi.logger.ESLogger;
import com.egemsoft.application.rickandmortyapi.model.Base;
import com.egemsoft.application.rickandmortyapi.model.Episode;
import com.egemsoft.application.rickandmortyapi.model.ResponseInfo;
import com.egemsoft.application.rickandmortyapi.model.RestResponse;
import com.egemsoft.application.rickandmortyapi.repository.EpisodeRepository;
import com.egemsoft.application.rickandmortyapi.service.EpisodeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * Service for operations of Episodes
 *
 * @author Shahriyar Novruzov
 * since 1.0
 */
@Service
public class EpisodeServiceImpl extends AbstractServiceImpl<Episode> implements EpisodeService {

    private static final ESLogger logger = ESLogger.getLogger(EpisodeServiceImpl.class);
    private static final String SORT_BY_NAME = "name";
    private static final String SORT_BY_CHARACTER = "character";
    private final static String EPISODE_PAGE_URL = "/episode/?page=";

    private final EpisodeRepository episodeRepository;
    private final String url;

    /**
     * @param episodeRepository injected Episode repository for getting records
     * @param url               root url for application
     */
    public EpisodeServiceImpl(EpisodeRepository episodeRepository,
                              @Value("${ms.full.url}") String url) {
        this.episodeRepository = episodeRepository;
        this.url = url;
    }

    /**
     * @param pageNumber requested page
     * @return found Episodes on requested page
     */
    @Override
    public RestResponse<List<Episode>> findPaginated(Integer pageNumber, String sortBy) {
        logger.debug("findPaginated pageNumber: {}", pageNumber);
        List<Episode> episodes = episodeRepository.getEpisodes();
        episodes.sort(getComparator(sortBy));
        ResponseInfo responseInfo = getResponseInfo(episodes.size(), pageNumber, url.concat(EPISODE_PAGE_URL));
        List<Episode> paginatedEpisodes = findPaginatedData(episodes, pageNumber);

        return RestResponse.of(paginatedEpisodes, responseInfo);
    }

    private Comparator<Episode> getComparator(String sortBy) {
        if (sortBy == null)
            return Comparator.comparing(Base::getId);
        else if (sortBy.equals(SORT_BY_NAME))
            return Comparator.comparing(Episode::getName);
        else if (sortBy.equals(SORT_BY_CHARACTER))
            return Comparator.comparingInt(episode -> episode.getCharacters().size());
        else
            return Comparator.comparing(Base::getId);
    }

    /**
     * @param id Episode id
     * @return found Episode
     */
    @Override
    public Episode findById(Long id) {
        logger.debug("findById id: {}", id);
        return super.findById(episodeRepository.getEpisodes(), id);
    }
}
