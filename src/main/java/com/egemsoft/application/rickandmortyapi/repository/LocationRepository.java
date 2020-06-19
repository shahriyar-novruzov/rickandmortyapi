package com.egemsoft.application.rickandmortyapi.repository;

import com.egemsoft.application.rickandmortyapi.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for store Locations in memory
 */
@Repository
public class LocationRepository {

    private List<Location> locations = new ArrayList<>();

    /**
     * @param paginatedLocations Locations on every page
     */
    public void addAll(List<Location> paginatedLocations) {
        this.locations.addAll(paginatedLocations);
    }

    /**
     * @return all Locations in store
     */
    public List<Location> getLocations() {
        return this.locations;
    }
}
