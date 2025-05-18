package com.edreams.convert.katas.rover.models;

public class Planet {

    private final String name;
    private final Plateau plateau;

    public Planet(String name, Plateau plateau) {
        this.name = name;
        this.plateau = plateau;
    }

    public String getName() {
        return name;
    }

    public Plateau getPlateau() {
        return plateau;
    }
}
