package com.edreams.convert.katas.rover.controllers;

import com.edreams.convert.katas.rover.MarsRover;
import com.edreams.convert.katas.rover.models.Plateau;
import com.edreams.convert.katas.rover.models.Position;

public class RoverController {

    private MarsRover marsRover;

    public String sendCommand(String command) throws IllegalArgumentException, IllegalStateException {
        if (marsRover == null) {
            throw new IllegalStateException("Mars Rover is not initialized.");
        }
        return marsRover.execute(command);
    }

    public MarsRover initializeMarsRover(Position initialPosition, Plateau plateau) {
        if (marsRover == null) {
            marsRover = new MarsRover(initialPosition, plateau);
            return marsRover;
        }
        return marsRover;
    }
}
