package com.edreams.convert.katas.rover;

import com.edreams.convert.katas.rover.models.Direction;
import com.edreams.convert.katas.rover.models.Planet;
import com.edreams.convert.katas.rover.models.Plateau;
import com.edreams.convert.katas.rover.models.Position;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static HashMap<String, Planet> planetMap;
    static {
        planetMap = new HashMap<>();
        planetMap.put("Mars", new Planet("Mars", new Plateau(10, 10)));
        planetMap.put("Venus", new Planet("Venus", new Plateau(15, 15)));
        planetMap.put("Mercury", new Planet("Mercury", new Plateau(5, 5)));
        planetMap.put("Jupiter", new Planet("Jupiter", new Plateau(200, 200)));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Mars Rover CLI!");
        System.out.println("Type the planet's name (e.g., Mars, Venus, Mercury, Jupiter) to select a planet:");
        for (String planetName : planetMap.keySet()) {
            System.out.println("- " + planetName);
        }
        System.out.print("Enter planet name: ");
        String planetName = scanner.nextLine().trim();
        Planet selectedPlanet = planetMap.get(planetName);
        if (selectedPlanet == null) {
            System.out.println("Invalid planet name. Exiting the program.");
            return;
        }
        System.out.println("You selected: " + selectedPlanet.getName() + " Plateau of size " + selectedPlanet.getPlateau().getWidth() + "x" + selectedPlanet.getPlateau().getHeight());

        System.out.println("Type the deployment position (e.g., 0 0 N) to deploy the rover (default position 0 0 N):");
        String position = scanner.nextLine().trim();
        String[] positionParts = position.split(" ");
        Position startingPosition = new Position(0, 0, Direction.NORTH);
        if (positionParts.length == 3) {
            try {
                int x = Integer.parseInt(positionParts[0]);
                int y = Integer.parseInt(positionParts[1]);
                Direction direction = Direction.fromAbbreviation(positionParts[2].toUpperCase());
                if (selectedPlanet.getPlateau().isValidPosition(x, y)) {
                    startingPosition = new Position(x, y, direction);
                } else {
                    System.out.println("Invalid position. Using default position 0 0 N.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid position format. Using default position 0 0 N.");
            }
        }

        MarsRover marsRover = new MarsRover(startingPosition, selectedPlanet.getPlateau());
        System.out.println("Rover deployed at: " + startingPosition);

        System.out.println("Type your commands or type 'exit' to quit.");

        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine().trim();

            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Exiting the program. Goodbye!");
                break;
            }

            System.out.println("You entered: " + input);
            String output = marsRover.execute(input);
            System.out.println(output);
        }

        scanner.close();
    }
}