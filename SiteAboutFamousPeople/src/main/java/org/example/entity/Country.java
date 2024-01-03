package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "countries")
public class Country extends BaseEntity {
    @OneToMany
    private List<Place> places = new ArrayList<>();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country country)) return false;
        return Objects.equals(getName(), country.getName()) && Objects.equals(getUrlFlag(), country.getUrlFlag());
    }
}
