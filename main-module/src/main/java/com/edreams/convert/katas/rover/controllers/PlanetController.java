package com.edreams.convert.katas.rover.controllers;

import com.edreams.convert.katas.rover.models.Planet;
import com.edreams.convert.katas.rover.models.Plateau;

import java.util.HashMap;
import java.util.Set;

public class PlanetController {
     private final HashMap<String, Planet> planetMap;

        public PlanetController() {
            planetMap = new HashMap<>();
            planetMap.put("Mars", new Planet("Mars", new Plateau(10, 10)));
            planetMap.put("Venus", new Planet("Venus", new Plateau(15, 15)));
            planetMap.put("Mercury", new Planet("Mercury", new Plateau(5, 5)));
            planetMap.put("Jupiter", new Planet("Jupiter", new Plateau(200, 200)));
        }

        public Planet getPlanet(String planetName) {
            return planetMap.get(planetName);
        }

        public Set<String> getAvailablePlanets() {
            return planetMap.keySet();
        }
}
