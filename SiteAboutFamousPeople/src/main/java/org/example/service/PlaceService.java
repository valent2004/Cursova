package org.example.service;

import org.example.entity.Place;
import org.example.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlaceService {
    @Autowired
    public PlaceService(PlaceRepository repository) {
        this.repository = repository;
    }

    private final PlaceRepository repository;

    public List<Place> getPlaces()
    {
        return repository.findAll();
    }

    public Place getPlaceById(Long id)
    {
        return repository.getReferenceById(id);
    }

    public List<Place> getPlaceByName(String name)
    {
        return repository.findAllByNameContains(name);
    }

    public void createPlace(Place place){repository.save(place);}

    public void deletePlace(Long id){repository.deleteById(id);}

    public Page<Place> sortPlacesByName(int pageNo, int pageSize, String sortBy, String sortOrder)
    {
        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return repository.findAll(pageable);
    }

    public void updatePlace(Place place, Long id){
        repository.findAll().set(id.intValue(), place);
    }
}
