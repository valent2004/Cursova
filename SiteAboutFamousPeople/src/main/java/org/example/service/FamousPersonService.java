package org.example.service;

import org.example.entity.Continent;
import org.example.entity.FamousPerson;
import org.example.repository.FamousPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamousPersonService {
    @Autowired
    public FamousPersonService(FamousPersonRepository repository) {
        this.repository = repository;
    }

    private final FamousPersonRepository repository;

    public List<FamousPerson> getFamousPeople()
    {
        return repository.findAll();
    }

    public FamousPerson getFamousPersonById(Long id)
    {
        return repository.getReferenceById(id);
    }

    public List<FamousPerson> getFamousPersonByName(String name)
    {
        return repository.findAllByNameContains(name);
    }

    public List<FamousPerson> getFamousPersonBySurname(String surname)
    {
        return repository.findAllBySurnameContains(surname);
    }

    public List<FamousPerson> getFamousPersonByMiddleName(String middleName)
    {
        return repository.findAllByMiddleNameContains(middleName);
    }

    public List<FamousPerson> getFamousPersonByBirthContinent(Continent continent)
    {
        return repository.findAllByBirthContinent(continent);
    }

    public List<FamousPerson> getFamousPersonByChronology(String chronology)
    {
        return repository.findAllByChronologyContains(chronology);
    }

    public void createFamousPerson(FamousPerson famousPerson){repository.save(famousPerson);}

    public void deleteFamousPerson(Long id){repository.deleteById(id);}

    public Page<FamousPerson> sortFamousPeopleByName(int pageNo, int pageSize, String sortBy, String sortOrder)
    {
        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return repository.findAll(pageable);
    }

    public void updateFamousPerson(FamousPerson famousPerson, Long id){
        repository.findAll().set(id.intValue(), famousPerson);
    }
}
