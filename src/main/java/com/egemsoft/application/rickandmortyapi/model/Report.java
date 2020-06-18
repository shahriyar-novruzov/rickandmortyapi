package com.egemsoft.application.rickandmortyapi.model;

import com.egemsoft.application.rickandmortyapi.model.enums.Endpoint;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class Report {

    private Map<Endpoint, Long> statistics;
    protected List<History> history;
}
