package com.egemsoft.application.rickandmortyapi.controller;

import com.egemsoft.application.rickandmortyapi.model.Episode;
import com.egemsoft.application.rickandmortyapi.model.RestResponse;
import com.egemsoft.application.rickandmortyapi.service.EpisodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/episode")
public class EpisodeController {

    private final EpisodeService episodeService;

    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @GetMapping
    public RestResponse<List<Episode>> findCharacters(@RequestParam(name = "page") @Nullable Integer pageNumber) {
        return RestResponse.of(episodeService.findEpisodes(pageNumber));
    }

    @GetMapping("/{id}")
    public Episode findById(@PathVariable @NotNull Long id) {
        return episodeService.findById(id);
    }
}
