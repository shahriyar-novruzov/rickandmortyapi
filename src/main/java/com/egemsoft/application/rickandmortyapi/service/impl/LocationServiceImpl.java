package com.egemsoft.application.rickandmortyapi.service.impl;

import com.egemsoft.application.rickandmortyapi.exception.NotFoundException;
import com.egemsoft.application.rickandmortyapi.helper.ReportingHelper;
import com.egemsoft.application.rickandmortyapi.logger.ESLogger;
import com.egemsoft.application.rickandmortyapi.model.Location;
import com.egemsoft.application.rickandmortyapi.model.ResponseInfo;
import com.egemsoft.application.rickandmortyapi.model.RestResponse;
import com.egemsoft.application.rickandmortyapi.model.enums.Endpoint;
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

    private static final ESLogger logger = ESLogger.getLogger(LocationServiceImpl.class);
    private static final String LOCATION_PAGE_URL = "/location/?page=";
    private static String ERROR_MESSAGE = null;

    private final LocationRepository locationRepository;
    private final ReportingHelper reportingHelper;
    private final String url;

    /**
     * @param locationRepository Location repository for getting records
     * @param url                root url for application
     */
    public LocationServiceImpl(LocationRepository locationRepository,
                               ReportingHelper reportingHelper,
                               @Value("${ms.full.url}") String url) {
        this.locationRepository = locationRepository;
        this.reportingHelper = reportingHelper;
        this.url = url;
    }

    /**
     * @param pageNumber requested page
     * @return found Locations on requested page
     */
    @Override
    public RestResponse<List<Location>> findPaginated(Integer pageNumber) {
        logger.debug("findPaginated pageNumber: {}", pageNumber);

        List<Location> locations = locationRepository.getLocations();
        ResponseInfo responseInfo = getResponseInfo(locations.size(), pageNumber, url.concat(LOCATION_PAGE_URL));
        List<Location> paginatedLocations = findPaginatedData(locations, pageNumber);
        reportingHelper.addHistory(Endpoint.LOCATION, ERROR_MESSAGE, RestResponse.of(paginatedLocations, responseInfo));

        return RestResponse.of(paginatedLocations, responseInfo);
    }

    /**
     * @param id Location id
     * @return found Location
     */
    @Override
    public Location findById(Long id) {
        logger.debug("findById id: {}", id);

        try {
            Location location = findById(locationRepository.getLocations(), id);
            reportingHelper.addHistory(Endpoint.LOCATION, ERROR_MESSAGE, location);
            return location;
        } catch (NotFoundException e) {
            reportingHelper.addHistory(Endpoint.LOCATION, e.getMessage(), null);
            throw e;
        }
    }
}
