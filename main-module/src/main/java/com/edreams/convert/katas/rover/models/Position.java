package com.edreams.convert.katas.rover.models;

public class Position {

    private int x;
    private int y;
    private Direction direction;

    public Position(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public Direction turnLeft() {
        direction = direction.turnLeft();
        return direction;
    }

    public Direction turnRight() {
        direction = direction.turnRight();
        return direction;
    }

    public Direction turnAround() {
        direction = direction.turnAround();
        return direction;
    }

    @Override
    public String toString() {
        return "Position {" +
                "X = " + x +
                ", Y = " + y +
                ", Direction = " + direction +
                '}';
    }
}
