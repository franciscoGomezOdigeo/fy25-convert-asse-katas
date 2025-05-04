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
        switch (compassDirection) {
            case "N":
                if (positionY + 1 >= this.plateau[0].length) {
                    compassDirection = turnAround(compassDirection);
                    System.out.println("Rover turned around to face " + compassDirection);
                } else {
                    positionY++;
                    this.plateau[positionX][positionY] = "";
                    this.position = positionX + "," + positionY;
                }
                break;
            case "E":
                if (positionX + 1 >= this.plateau.length) {
                    compassDirection = turnAround(compassDirection);
                    System.out.println("Rover turned around to face " + compassDirection);
                } else {
                    positionX++;
                    this.plateau[positionX][positionY] = "";
                    this.position = positionX + "," + positionY;
                }
                break;
            case "S":
                if (positionY - 1 < 0) {
                    compassDirection = turnAround(compassDirection);
                    System.out.println("Rover turned around to face " + compassDirection);
                } else {
                    positionY--;
                    this.plateau[positionX][positionY] = "";
                    this.position = positionX + "," + positionY;
                }
                break;
            case "W":
                if (positionX - 1 < 0) {
                    compassDirection = turnAround(compassDirection);
                    System.out.println("Rover turned around to face " + compassDirection);
                } else {
                    positionX--;
                    this.plateau[positionX][positionY] = "";
                    this.position = positionX + "," + positionY;
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid compass direction: " + compassDirection);
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
