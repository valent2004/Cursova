package org.example.service;

import org.example.entity.Country;
import org.example.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    private final CountryRepository repository;

    public List<Country> getCountries()
    {
        return repository.findAll();
    }

    public Country getCountryById(Long id)
    {
        return repository.getReferenceById(id);
    }

    public List<Country> getCountryByName(String name)
    {
        return repository.findAllByNameContains(name);
    }

    public void createCountry(Country country){repository.save(country);}

    public void deleteCountry(Long id){repository.deleteById(id);}

    public Page<Country> sortCountriesByName(int pageNo, int pageSize, String sortBy, String sortOrder)
    {
        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return repository.findAll(pageable);
    }

    public void updateCountry(Country country, Long id){
        repository.findAll().set(id.intValue(), country);
    }
}
