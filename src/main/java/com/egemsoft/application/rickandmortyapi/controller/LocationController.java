package com.egemsoft.application.rickandmortyapi.controller;

import com.egemsoft.application.rickandmortyapi.model.Location;
import com.egemsoft.application.rickandmortyapi.model.RestResponse;
import com.egemsoft.application.rickandmortyapi.service.LocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "${ms.root.url}/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public RestResponse<List<Location>> findPaginated(@RequestParam(name = "page") @Nullable Integer pageNumber) {
        return locationService.findPaginated(pageNumber);
    }

    @GetMapping("/{id}")
    public Location findById(@PathVariable @NotNull Long id) {
        return locationService.findById(id);
    }
}
