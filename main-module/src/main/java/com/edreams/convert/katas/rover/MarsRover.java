package com.edreams.convert.katas.rover;

public class MarsRover {

    private final String[][] plateau;
    private String position;
    private String compassDirection;

    public MarsRover(String[][] plateau, String compassDirection) {
        this.plateau = plateau;
        this.position = "0,0";
        this.compassDirection = compassDirection;
    }

    public String[][] getPosition() {
        return this.plateau;
    }

    public String execute(String command) {
        String[] commands = command.split("");
        for (String cmd : commands) {
            switch (cmd) {
                case "M":
                    move();
                    break;
                case "L":
                    turn("L");
                    break;
                case "R":
                    turn("R");
                    break;
                default:
                    throw new IllegalArgumentException("Invalid command: " + cmd);
            }
        }
        return position + ":" + compassDirection;
    }

    private void move() {
        int positionX = Integer.parseInt(position.split(",")[0]);
        int positionY = Integer.parseInt(position.split(",")[1]);

        switch (this.compassDirection) {
            case "N" -> positionY = updatePosition(positionX, positionY + 1, "Y");
            case "E" -> positionX = updatePosition(positionX + 1, positionY, "X");
            case "S" -> positionY = updatePosition(positionX, positionY - 1, "Y");
            case "W" -> positionX = updatePosition(positionX - 1, positionY, "X");
            default -> throw new IllegalArgumentException("Invalid compass direction: " + this.compassDirection);
        }

        this.position = positionX + "," + positionY;
        this.plateau[positionX][positionY] = "";
    }

    private int updatePosition(int newX, int newY, String axis) {
        if ("X".equals(axis)) {
            if (newX < 0 || newX >= this.plateau.length) {
                this.compassDirection = turnAround(this.compassDirection);
                System.out.println("Rover turned around to face " + this.compassDirection);
                return Integer.parseInt(position.split(",")[0]);
            }
            return newX;
        } else {
            if (newY < 0 || newY >= this.plateau[0].length) {
                this.compassDirection = turnAround(this.compassDirection);
                System.out.println("Rover turned around to face " + this.compassDirection);
                return Integer.parseInt(position.split(",")[1]);
            }
            return newY;
        }
    }

    private void turn(String direction) {
        switch (direction) {
            case "L":
                this.compassDirection = turnLeft(compassDirection);
                break;
            case "R":
                this.compassDirection = turnRight(compassDirection);
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }
    }

    private String turnLeft(String direction) {
        return switch (direction) {
            case "N" -> "W";
            case "W" -> "S";
            case "S" -> "E";
            case "E" -> "N";
            default -> throw new IllegalArgumentException("Invalid compass direction: " + direction);
        };
    }

    private String turnRight(String direction) {
        return switch (direction) {
            case "N" -> "E";
            case "E" -> "S";
            case "S" -> "W";
            case "W" -> "N";
            default -> throw new IllegalArgumentException("Invalid compass direction: " + direction);
        };
    }

    private String turnAround(String direction) {
        return switch (direction) {
            case "N" -> "S";
            case "E" -> "W";
            case "S" -> "N";
            case "W" -> "E";
            default -> throw new IllegalArgumentException("Invalid compass direction: " + direction);
        };
    }
}
