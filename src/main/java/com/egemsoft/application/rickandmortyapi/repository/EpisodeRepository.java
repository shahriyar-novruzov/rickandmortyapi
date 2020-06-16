package com.egemsoft.application.rickandmortyapi.repository;

import com.egemsoft.application.rickandmortyapi.model.Episode;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EpisodeRepository {

    private List<Episode> episodes = new ArrayList<>();

    public void addAll(List<Episode> episodes) {
        this.episodes.addAll(episodes);
    }

    public List<Episode> getEpisodes() {
        return this.episodes;
    }
}
