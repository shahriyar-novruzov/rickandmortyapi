package com.egemsoft.application.rickandmortyapi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RickAndMortyApi {

    private String characters;
    private String episodes;
    private String locations;
}
