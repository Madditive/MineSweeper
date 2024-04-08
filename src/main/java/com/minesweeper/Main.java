package com.minesweeper;

public class Main {
    public static void main(String[] args) {
        // Define default game settings.
        int width = 16; // Example width of the board
        int height = 16; // Example height of the board
        int mineCount = 40; // Example number of mines

        // Create a game instance with specified width, height, and mine count
        Game game = new Game(width, height, mineCount);
        game.start(); // Start the game
    }
}