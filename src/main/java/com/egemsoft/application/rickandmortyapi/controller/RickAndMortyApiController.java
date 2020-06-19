package com.egemsoft.application.rickandmortyapi.controller;

import com.egemsoft.application.rickandmortyapi.model.RickAndMortyApi;
import com.egemsoft.application.rickandmortyapi.service.RickAndMortyApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller for getting main endpoints in rickandmortyapi
 */
@Api("Rest Controller for getting main endpoints in rickandmortyapi")
@RestController
@RequestMapping(path = "${ms.root.url}")
public class RickAndMortyApiController {

    private final RickAndMortyApiService rickAndMortyApiService;

    public RickAndMortyApiController(RickAndMortyApiService rickAndMortyApiService) {
        this.rickAndMortyApiService = rickAndMortyApiService;
    }

    /**
     * @return main endpoints in rickandmortyapi
     */
    @ApiOperation("Get all main endpoints in rickandmortyapi")
    @GetMapping
    public RickAndMortyApi getRickAndMortyApi() {
        return rickAndMortyApiService.getRickAndMortyApi();
    }
}
