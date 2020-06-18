package com.egemsoft.application.rickandmortyapi.service;

import com.egemsoft.application.rickandmortyapi.model.Location;
import com.egemsoft.application.rickandmortyapi.model.RestResponse;

import java.util.List;

public interface LocationService {
    RestResponse<List<Location>> findPaginated(Integer pageNumber);

    Location findById(Long id);
}
