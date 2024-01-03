package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Continent;
import org.example.entity.FamousPerson;
import org.example.service.FamousPersonService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/famous_people")
@RequiredArgsConstructor
public class FamousPersonController {
    private FamousPersonService service;

    public FamousPersonController(FamousPersonService service) {
        this.service = service;
    }

    @PostMapping
    public void postFamousPerson(@RequestBody FamousPerson famousPerson) {
        service.createFamousPerson(famousPerson);
    }

    @GetMapping
    public List<FamousPerson> getFamousPeople() {
        return service.getFamousPeople();
    }

    @GetMapping("/{id}")
    public FamousPerson getFamousPersonById(@PathVariable Long id) {
        return service.getFamousPersonById(id);
    }

    @GetMapping("/sort/{sortBy}")
    public ResponseEntity<Page<FamousPerson>> sortFamousPersonByName(@RequestParam(defaultValue = "0") int pageNo,
                                                                     @RequestParam(defaultValue = "10") int pageSize,
                                                                     @PathVariable String sortBy,
                                                                     @RequestParam(defaultValue = "asc") String sortOrder) {
        Page<FamousPerson> famousPeoplePage = service.sortFamousPeopleByName(pageNo, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(famousPeoplePage, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public List<FamousPerson> getFamousPersonByName(@PathVariable String name) {
        return service.getFamousPersonByName(name);
    }

    @GetMapping("/{surname}")
    public List<FamousPerson> getFamousPersonBySurname(@PathVariable String surname) {
        return service.getFamousPersonBySurname(surname);
    }

    @GetMapping("/{middleName}")
    public List<FamousPerson> getFamousPersonByMiddleName(@PathVariable String middleName) {
        return service.getFamousPersonByMiddleName(middleName);
    }

    @GetMapping("/{birthContinent}")
    public List<FamousPerson> getFamousPersonByBirthContinent(@PathVariable Continent birthContinent) {
        return service.getFamousPersonByBirthContinent(birthContinent);
    }

    @GetMapping("/{chronology}")
    public List<FamousPerson> getFamousPersonByChronology(@PathVariable String chronology) {
        return service.getFamousPersonByChronology(chronology);
    }

    @PutMapping("/{id}")
    public void updateFamousPerson(@PathVariable Long id, @RequestBody FamousPerson famousPerson) {
        service.updateFamousPerson(famousPerson, id);
    }

    @DeleteMapping("/{id}")
    public void removeFamousPerson(@PathVariable Long id) {
        service.deleteFamousPerson(id);
    }
}
