package com.edreams.convert.katas.rover;

import com.edreams.convert.katas.rover.models.Direction;
import com.edreams.convert.katas.rover.models.Plateau;
import com.edreams.convert.katas.rover.models.Position;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class MarsRoverTest {

    private MarsRover marsRover;

    @BeforeTest
    public void setUp() {
        Plateau plateau = new Plateau(5, 5);
        Position position = new Position(0, 0, Direction.NORTH);
        marsRover = new MarsRover(position, plateau);
    }

    @Test
    public void testMarsRover() {
        String output = marsRover.execute("MMRMM");
        assertEquals(output, "Position {X = 2, Y = 2, Direction = EAST}");
    }
}