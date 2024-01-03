package org.example.entity;

import lombok.Getter;

@Getter
public enum Continent {
    EUROPE("Europe"),
    ASIA("Asia"),
    AFRICA("Africa"),
    SOUTH_AMERICA("South America"),
    NORTH_AMERICA("North America"),
    AUSTRALIA("Australia");

    final private String name;

    private Continent(String name) {
        this.name = name;
    }
}
