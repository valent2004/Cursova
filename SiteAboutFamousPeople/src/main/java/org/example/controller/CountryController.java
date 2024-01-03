package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Country;
import org.example.service.CountryService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
@RequiredArgsConstructor
public class CountryController {
    private CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @PostMapping
    public void postCountry(@RequestBody Country country){ service.createCountry(country);}

    @GetMapping
    public List<Country> getCountries(){
        return service.getCountries();
    }

    @GetMapping("/{id}")
    public Country getCountryById(@PathVariable Long id){ return service.getCountryById(id); }

    @GetMapping("/sort/{sortBy}")
    public ResponseEntity<Page<Country>> sortCountryByName(@RequestParam(defaultValue = "0") int pageNo,
                                  @RequestParam(defaultValue = "10") int pageSize,
                                  @PathVariable String sortBy,
                                  @RequestParam(defaultValue = "asc") String sortOrder)
    {
        Page<Country> countryPage = service.sortCountriesByName(pageNo, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(countryPage, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public List<Country> getCountryByName(@PathVariable String name){ return service.getCountryByName(name); }

    @PutMapping("/{id}")
    public void updateCountry(@PathVariable Long id, @RequestBody Country country){service.updateCountry(country, id);}

    @DeleteMapping("/{id}")
    public void removeCountry(@PathVariable Long id){service.deleteCountry(id);}
}
