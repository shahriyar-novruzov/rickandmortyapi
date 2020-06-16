package com.egemsoft.application.rickandmortyapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RickAndMortyApi {

    private String characters;
    private String episodes;
    private String locations;
}
