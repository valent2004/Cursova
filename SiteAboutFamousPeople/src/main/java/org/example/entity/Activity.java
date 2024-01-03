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
@Table(name = "activities")
public class Activity extends BaseEntity{
    @ManyToMany(mappedBy = "activities", cascade = CascadeType.PERSIST)
    private List<FamousPerson> famousPeople = new ArrayList<>();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity activity)) return false;
        return Objects.equals(getName(), activity.getName()) && Objects.equals(getUrlFlag(), activity.getUrlFlag());
    }
}
