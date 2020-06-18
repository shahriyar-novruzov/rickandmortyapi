package com.egemsoft.application.rickandmortyapi.model;

import com.egemsoft.application.rickandmortyapi.model.enums.Endpoint;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Statistics {

    private Endpoint endpoint;
    private Long count;
}
