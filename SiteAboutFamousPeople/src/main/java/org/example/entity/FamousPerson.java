package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "famous_people")
public class FamousPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "surname")
    private String surname;
    @Column(name = "name")
    private String name;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @ManyToOne
    @JoinTable(name = "birth_place_id", joinColumns = @JoinColumn(name = "place_id"), inverseJoinColumns = @JoinColumn(name="famous_person_id"))
    private Place birthPlace;
    @ManyToOne
    @JoinTable(name = "birth_country_id", joinColumns = @JoinColumn(name = "country_id"), inverseJoinColumns = @JoinColumn(name="famous_person_id"))
    private Country birthCountry;
    @Enumerated(EnumType.STRING)
    @Column(name = "birth_continent")
    private Continent birthContinent;
    @ManyToMany
    @JoinTable(
            name = "famous_person_activities",
            joinColumns = @JoinColumn(name = "famous_person_id"),
            inverseJoinColumns = @JoinColumn(name="activity_id"))
    private Set<Activity> activities = new HashSet<>();
    @Column(name = "chronology")
    private String chronology;
    @Column(name = "url_photo")
    private String urlPhoto;


}

