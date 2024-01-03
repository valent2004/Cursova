package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Place;
import org.example.service.PlaceService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/place")
@RequiredArgsConstructor
public class PlaceController {
    private PlaceService service;

    public PlaceController(PlaceService service) {
        this.service = service;
    }

    @PostMapping
    public void postPlace(@RequestBody Place place){ service.createPlace(place);}

    @GetMapping
    public List<Place> getPlace(){
        return service.getPlaces();
    }

    @GetMapping("/{id}")
    public Place getPlaceById(@PathVariable Long id){ return service.getPlaceById(id); }

    @GetMapping("/sort/{sortBy}")
    public ResponseEntity<Page<Place>> sortPlaceByName(@RequestParam(defaultValue = "0") int pageNo,
                                                         @RequestParam(defaultValue = "10") int pageSize,
                                                         @PathVariable String sortBy,
                                                         @RequestParam(defaultValue = "asc") String sortOrder)
    {
        Page<Place> placesPage = service.sortPlacesByName(pageNo, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(placesPage, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public List<Place> getPlaceByName(@PathVariable String name){ return service.getPlaceByName(name); }

    @PutMapping("/{id}")
    public void updatePlace(@PathVariable Long id, @RequestBody Place place){service.updatePlace(place, id);}

    @DeleteMapping("/{id}")
    public void removePlace(@PathVariable Long id){service.deletePlace(id);}
}
