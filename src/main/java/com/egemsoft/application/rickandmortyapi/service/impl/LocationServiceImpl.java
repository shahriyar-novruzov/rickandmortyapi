package com.egemsoft.application.rickandmortyapi.service.impl;

import com.egemsoft.application.rickandmortyapi.logger.ESLogger;
import com.egemsoft.application.rickandmortyapi.model.Location;
import com.egemsoft.application.rickandmortyapi.model.ResponseInfo;
import com.egemsoft.application.rickandmortyapi.model.RestResponse;
import com.egemsoft.application.rickandmortyapi.repository.LocationRepository;
import com.egemsoft.application.rickandmortyapi.service.LocationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl extends AbstractServiceImpl<Location> implements LocationService {

    private static final ESLogger logger = ESLogger.getLogger(LocationServiceImpl.class);
    private final static String LOCATION_PAGE_URL = "/location/?page=";

    private final LocationRepository locationRepository;
    private final String url;

    public LocationServiceImpl(LocationRepository locationRepository,
                               @Value("${ms.full.url}") String url) {
        this.locationRepository = locationRepository;
        this.url = url;
    }

    @Override
    public RestResponse<List<Location>> findPaginated(Integer pageNumber) {
        logger.debug("findPaginated pageNumber: {}", pageNumber);
        List<Location> episodes = locationRepository.getLocations();
        ResponseInfo responseInfo = getResponseInfo(episodes.size(), pageNumber, url.concat(LOCATION_PAGE_URL));
        List<Location> paginatedEpisodes = super.findPaginatedData(episodes, pageNumber);

        return RestResponse.of(paginatedEpisodes, responseInfo);
    }

    @Override
    public Location findById(Long id) {
        logger.debug("findById id: {}", id);
        return super.findById(locationRepository.getLocations(), id);
    }
}
