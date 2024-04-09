package com.minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InputProvider inputProvider = new ConsoleInputProvider();
        System.out.println("Select difficulty:");
        System.out.println("1: Easy");
        System.out.println("2: Intermediate");
        System.out.println("3: Expert");
        int difficultyChoice = 0;
        try {
            String input = inputProvider.getNextInput().trim();
            difficultyChoice = Integer.parseInt(input); // Manually parse the integer
        } catch (NumberFormatException e) {
            return;
        }

        String difficulty = switch (difficultyChoice) {
            case 1 -> "EASY";
            case 2 -> "INTERMEDIATE";
            case 3 -> "EXPERT";
            default -> {
                System.out.println("Invalid selection. Defaulting to Easy.");
                yield "EASY";
            }
        };

        // Create a game instance with specified width, height, and mine count
        Game game = new Game(difficulty, inputProvider);
        game.start(); // Start the game
    }
}