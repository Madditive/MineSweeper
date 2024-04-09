package com.minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select difficulty:");
        System.out.println("1: Easy");
        System.out.println("2: Intermediate");
        System.out.println("3: Expert");
        int difficultChoice = scanner.nextInt();

        String difficulty;
        switch (difficultChoice) {
            case 1:
                difficulty = "EASY";
                break;
            case 2:
                difficulty = "INTERMEDIATE";
                break;
            case 3:
                difficulty = "EXPERT";
                break;
            default:
                System.out.println("Invalid selection. Defaulting to Easy.");
                difficulty = "EASY";
        }

        // Create a game instance with specified width, height, and mine count
        Game game = new Game(difficulty);
        game.start(); // Start the game
    }
}