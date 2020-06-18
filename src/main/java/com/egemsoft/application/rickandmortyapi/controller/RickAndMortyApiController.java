package com.egemsoft.application.rickandmortyapi.controller;

import com.egemsoft.application.rickandmortyapi.model.RickAndMortyApi;
import com.egemsoft.application.rickandmortyapi.service.RickAndMortyApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${ms.root.url}")
public class RickAndMortyApiController {

    private final RickAndMortyApiService rickAndMortyApiService;

    public RickAndMortyApiController(RickAndMortyApiService rickAndMortyApiService) {
        this.rickAndMortyApiService = rickAndMortyApiService;
    }

    @GetMapping
    public RickAndMortyApi getRickAndMortyApi() {
        return rickAndMortyApiService.getRickAndMortyApi();
    }
}
