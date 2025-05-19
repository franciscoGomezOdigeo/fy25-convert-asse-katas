package com.edreams.convert.katas.rover;

import com.edreams.convert.katas.rover.models.Plateau;
import com.edreams.convert.katas.rover.models.Position;

public class MarsRover {

    private Plateau plateau;

    private Position currentPosition;

    public MarsRover(Position position, Plateau plateau) {
        this.plateau = plateau;
        this.currentPosition = position;
    }

    //TODO: Optional execute command

    public String execute(String command) throws IllegalArgumentException {
        validateCommands(command);
        String[] commands = command.split("");
        int commandMultiplier = 1;
        for (String cmd : commands) {
            switch (cmd) {
                case "M":
                    for (int i = 0; i < commandMultiplier; i++) {
                        move();
                    }
                    break;
                case "L":
                    for (int i = 0; i < commandMultiplier; i++) {
                        turn("L");
                    }
                    break;
                case "R":
                    for (int i = 0; i < commandMultiplier; i++) {
                        turn("R");
                    }
                    break;
                default:
                    if (cmd.matches("[2-9]+")) {
                        commandMultiplier = Integer.parseInt(cmd);
                    } else {
                        throw new IllegalArgumentException("Invalid command: " + cmd);
                    }
            }
        }
        return this.currentPosition.toString();
    }

    private void validateCommands(String commands) throws IllegalArgumentException {
        if (commands == null || commands.isEmpty()) {
            throw new IllegalArgumentException("Commands cannot be null or empty");
        }
        for (char command : commands.toCharArray()) {
            if (!"MLR23456789".contains(String.valueOf(command))) {
                throw new IllegalArgumentException("Invalid command: " + command);
            }
        }

    }

    private void move() {
        int positionX = currentPosition.getX();
        int positionY = currentPosition.getY();

        switch (this.currentPosition.getDirection()) {
            case NORTH -> positionY = updatePosition(positionX, positionY + 1, "Y");
            case EAST -> positionX = updatePosition(positionX + 1, positionY, "X");
            case SOUTH -> positionY = updatePosition(positionX, positionY - 1, "Y");
            case WEST -> positionX = updatePosition(positionX - 1, positionY, "X");
        }

        this.currentPosition.setX(positionX);
        this.currentPosition.setY(positionY);
    }

    private int updatePosition(int newX, int newY, String axis) {
        if ("X".equals(axis)) {
            if (newX < 0 || newX >= this.plateau.getWidth()) {
                this.currentPosition.turnAround();
                System.out.println("Rover turned around to face " + this.currentPosition.getDirection());
                return currentPosition.getX();
            }
            return newX;
        } else {
            if (newY < 0 || newY >= this.plateau.getHeight()) {
                this.currentPosition.turnAround();
                System.out.println("Rover turned around to face " + this.currentPosition.getDirection());
                return currentPosition.getY();
            }
            return newY;
        }
    }

    private void turn(String direction) throws IllegalArgumentException {
        switch (direction) {
            case "L":
                this.currentPosition.turnLeft();
                break;
            case "R":
                this.currentPosition.turnRight();
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }

    }
}
