package com.edreams.convert.katas.rover.models;

public enum Direction {
    NORTH("N"), SOUTH("S"), EAST("E"), WEST("W");

    private final String abbreviation;

    Direction(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public static Direction fromAbbreviation(String abbreviation) {
        for (Direction direction : values()) {
            if (direction.abbreviation.equalsIgnoreCase(abbreviation)) {
                return direction;
            }
        }
        throw new IllegalArgumentException("No enum constant for abbreviation: " + abbreviation);
    }

    public Direction turnLeft() {
        return switch (this) {
            case NORTH -> WEST;
            case SOUTH -> EAST;
            case EAST -> NORTH;
            case WEST -> SOUTH;
        };
    }

    public Direction turnRight() {
        return switch (this) {
            case NORTH -> EAST;
            case SOUTH -> WEST;
            case EAST -> SOUTH;
            case WEST -> NORTH;
        };
    }

    public Direction turnAround() {
        return switch (this) {
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case EAST -> WEST;
            case WEST -> EAST;
        };
    }
}
