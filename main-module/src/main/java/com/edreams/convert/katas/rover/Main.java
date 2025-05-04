package com.edreams.convert.katas.rover;

import java.util.Scanner;

public class Main {

    public static String[][] plateau = new String[10][10];

    public static void main(String[] args) {
        plateau[0][0] = "";
        MarsRover marsRover = new MarsRover(plateau, "N");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Mars Rover CLI!");
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