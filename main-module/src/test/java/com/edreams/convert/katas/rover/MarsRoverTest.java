package com.edreams.convert.katas.rover;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class MarsRoverTest {

    private MarsRover marsRover;

    @BeforeTest
    public void setUp() {
        String[][] plateau = new String[10][10];
        marsRover = new MarsRover(plateau, "N");
    }

    @Test
    public void testMarsRover() {
        String output = marsRover.execute("MMRMM");
        assertEquals(output, "2,2:E");
    }

    @Test
    public void testTurnAround() {
        String output = marsRover.execute("LMM");
        assertEquals(output, "1,0:E");
    }

}