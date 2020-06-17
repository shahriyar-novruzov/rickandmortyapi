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

/**
 * Service for operations of Locations
 *
 * @author Shahriyar Novruzov
 * since 1.0
 */
@Service
public class LocationServiceImpl extends AbstractServiceImpl<Location> implements LocationService {

    /**
     * Safe Egemsoft logger
     */
    private static final ESLogger logger = ESLogger.getLogger(LocationServiceImpl.class);
    /**
     * Location api url
     */
    private final static String LOCATION_PAGE_URL = "/location/?page=";

    /**
     * Data store for Locations
     */
    private final LocationRepository locationRepository;
    /**
     * Root url for application
     */
    private final String url;

    /**
     * @param locationRepository injected Location repository for getting records
     * @param url                root url for application
     */
    public LocationServiceImpl(LocationRepository locationRepository,
                               @Value("${ms.full.url}") String url) {
        this.locationRepository = locationRepository;
        this.url = url;
    }

    /**
     * @param pageNumber requested page
     * @return found Locations on requested page
     */
    @Override
    public RestResponse<List<Location>> findPaginated(Integer pageNumber) {
        logger.debug("findPaginated pageNumber: {}", pageNumber);
        List<Location> episodes = locationRepository.getLocations();
        ResponseInfo responseInfo = getResponseInfo(episodes.size(), pageNumber, url.concat(LOCATION_PAGE_URL));
        List<Location> paginatedEpisodes = super.findPaginatedData(episodes, pageNumber);

        return RestResponse.of(paginatedEpisodes, responseInfo);
    }

    /**
     * @param id Location id
     * @return found Location
     */
    @Override
    public Location findById(Long id) {
        logger.debug("findById id: {}", id);
        return super.findById(locationRepository.getLocations(), id);
    }
}
