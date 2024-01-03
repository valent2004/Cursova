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
@Table(name = "places")
public class Place extends BaseEntity {
    @OneToMany
    private List<Activity> activity = new ArrayList<>();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Place place)) return false;
        return Objects.equals(getName(), place.getName()) && Objects.equals(getUrlFlag(), place.getUrlFlag());
    }
}
