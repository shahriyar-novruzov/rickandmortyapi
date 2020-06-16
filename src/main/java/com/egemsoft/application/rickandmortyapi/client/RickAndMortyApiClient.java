package com.egemsoft.application.rickandmortyapi.client;

import com.egemsoft.application.rickandmortyapi.config.FeignSSLConfig;
import com.egemsoft.application.rickandmortyapi.model.Character;
import com.egemsoft.application.rickandmortyapi.model.Episode;
import com.egemsoft.application.rickandmortyapi.model.Location;
import com.egemsoft.application.rickandmortyapi.model.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Primary
@FeignClient(name = "account",
        url = "${client.rickandmortyapi.url}",
        fallback = RickAndMortyApiClientFallback.class, configuration = FeignSSLConfig.class)
public interface RickAndMortyApiClient {

    @GetMapping("/episode?page={pageNumber}")
    RestResponse<List<Episode>> getEpisodes(@RequestParam("pageNumber") Integer pageNumber);

    @GetMapping("/character?page={pageNumber}")
    RestResponse<List<Character>> getCharacters(@RequestParam("pageNumber") Integer pageNumber);
}
