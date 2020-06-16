package com.egemsoft.application.rickandmortyapi.repository;

import com.egemsoft.application.rickandmortyapi.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LocationRepository {

    private List<Location> locations = new ArrayList<>();

    public void addAll(List<Location> locations) {
        this.locations.addAll(locations);
    }

    public List<Location> getLocations() {
        return this.locations;
    }
}
