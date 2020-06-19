package com.egemsoft.application.rickandmortyapi.controller;

import com.egemsoft.application.rickandmortyapi.model.Location;
import com.egemsoft.application.rickandmortyapi.model.RestResponse;
import com.egemsoft.application.rickandmortyapi.service.LocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Rest Controller for Location operations
 */
@Api("Rest Controller for Location operations")
@RestController
@RequestMapping(path = "${ms.root.url}/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    /**
     * @param pageNumber optional requested page number, if not exists, will be considered as page 1
     * @return Locations on the page
     */
    @ApiOperation("Find Locations on requested page")
    @GetMapping
    public RestResponse<List<Location>> findPaginated(
            @RequestParam(name = "page", required = false) Integer pageNumber) {
        return locationService.findPaginated(pageNumber);
    }

    /**
     * @param id Location id
     * @return found Location
     */
    @ApiOperation("Find Location by id")
    @GetMapping("/{id}")
    public Location findById(@PathVariable @NotNull Long id) {
        return locationService.findById(id);
    }
}
