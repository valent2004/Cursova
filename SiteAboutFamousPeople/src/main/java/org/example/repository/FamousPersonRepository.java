package org.example.repository;

import org.example.entity.Continent;
import org.example.entity.FamousPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamousPersonRepository extends JpaRepository<FamousPerson, Long> {
    List<FamousPerson> findAllByNameContains(String name);

    List<FamousPerson> findAllBySurnameContains(String surname);

    List<FamousPerson> findAllByMiddleNameContains(String middleName);

    List<FamousPerson> findAllByBirthContinent(Continent birthContinent);

    List<FamousPerson> findAllByChronologyContains(String chronology);
}
