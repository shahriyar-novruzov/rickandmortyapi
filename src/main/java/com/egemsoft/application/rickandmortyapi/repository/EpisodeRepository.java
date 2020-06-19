package com.egemsoft.application.rickandmortyapi.repository;

import com.egemsoft.application.rickandmortyapi.model.Episode;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for store Episodes in memory
 */
@Repository
public class EpisodeRepository {

    private List<Episode> episodes = new ArrayList<>();

    /**
     * @param paginatedEpisodes Episodes on every page
     */
    public void addAll(List<Episode> paginatedEpisodes) {
        this.episodes.addAll(paginatedEpisodes);
    }

    /**
     * @return all Episodes in store
     */
    public List<Episode> getEpisodes() {
        return this.episodes;
    }
}
