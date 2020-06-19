package com.egemsoft.application.rickandmortyapi.controller;

import com.egemsoft.application.rickandmortyapi.model.Episode;
import com.egemsoft.application.rickandmortyapi.model.RestResponse;
import com.egemsoft.application.rickandmortyapi.service.EpisodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@Api("Rest Controller for Episode operations")
@RestController
@RequestMapping(path = "${ms.root.url}/episode")
public class EpisodeController {

    private final EpisodeService episodeService;

    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    /**
     * @param pageNumber optional requested page number, if not exists, will be considered as page 1
     * @param sortBy optional sort parameter
     * @return Episodes on the page
     */
    @ApiOperation("Find Episodes on requested page, also sorted by name or character count")
    @GetMapping
    public RestResponse<List<Episode>> findPaginated(@RequestParam(name = "page", required = false) Integer pageNumber,
                                                     @RequestParam(name = "sortBy", required = false) String sortBy) {
        return episodeService.findPaginated(pageNumber, sortBy);
    }

    /**
     * @param id Episode id
     * @return found Episode
     */
    @ApiOperation("Find Episode by id")
    @GetMapping("/{id}")
    public Episode findById(@PathVariable @NotNull Long id) {
        return episodeService.findById(id);
    }
}
