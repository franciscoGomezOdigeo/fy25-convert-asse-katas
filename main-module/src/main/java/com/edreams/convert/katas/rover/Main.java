package com.edreams.convert.katas.rover;

import com.edreams.convert.katas.rover.controllers.PlanetController;
import com.edreams.convert.katas.rover.controllers.RoverController;
import com.edreams.convert.katas.rover.models.Direction;
import com.edreams.convert.katas.rover.models.Planet;
import com.edreams.convert.katas.rover.models.Position;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        RoverController roverController = new RoverController();
        PlanetController planetController = new PlanetController();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Mars Rover CLI!");
        // -- START Planet Selection PARAMS name --
        System.out.println("Type the planet's name (e.g., Mars, Venus, Mercury, Jupiter) to select a planet:");
        for (String planetName : planetController.getAvailablePlanets()) {
            System.out.println("- " + planetName);
        }
        System.out.print("Enter planet name: ");
        String planetName = scanner.nextLine().trim();
        Planet selectedPlanet = planetController.getPlanet(planetName);
        if (selectedPlanet == null) {
            System.out.println("Invalid planet name. Exiting the program.");
            return;
        }
        System.out.println("You selected: " + selectedPlanet.getName() + " Plateau of size " + selectedPlanet.getPlateau().getWidth() + "x" + selectedPlanet.getPlateau().getHeight());
        // -- END Planet Selection RETURN Planet --

        System.out.println("Type the deployment position (e.g., 0 0 N) to deploy the rover (default position 0 0 N):");
        // -- START Initial Deployment PARAMS x y Direction --
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
        // -- END Initial Deployment RETURN Position --

        // -- START Rover Deployment PARAMS Position, Plateau --
        roverController.initializeMarsRover(startingPosition, selectedPlanet.getPlateau());
        System.out.println("Rover deployed at: " + startingPosition);
        // -- END Rover Deployment RETURN MarsRover --

        System.out.println("Type your commands or type 'exit' to quit.");

        // -- START Main CLI command cycle --
        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine().trim();

            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Exiting the program. Goodbye!");
                break;
            }

            System.out.println("You entered: " + input);
            String output = roverController.sendCommand(input);
            System.out.println(output);
        }
        // -- END --

        scanner.close();
    }

}