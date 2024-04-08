package com.minesweeper;

import java.util.Scanner;

public class Game {
    private Board board;
    private Scanner scanner;
    private boolean isGameOver;
    private int width;
    private int height;
    private int mineCount;

    public Game(int width, int height, int mineCount) {
        this.width = width;
        this.height = height;
        this.mineCount = mineCount;
        this.board = new Board(width, height, mineCount);
        this.scanner = new Scanner(System.in);
        this.isGameOver = false;
    }

    public void start() {
        System.out.println("Welcome to Minesweeper!");
        // Display the initial empty board
        board.displayBoard();
        // Prompt user for the first move
        System.out.println("Enter row and column for the first move (e.g., 2B):");
        int[] firstMove = promptFirstMove();
        // Place mines safely and calculate adjacent mines
        board.placeMinesSafely(firstMove[0], firstMove[1]);
        // Now, handle the first move specifically

        // Placeholder for the main game loop
        while (!isGameOver) {
            board.displayBoard();
            // Implement game loop logic here, including prompting for and handling further moves
            System.out.println("Enter your next move (row column):");
            int[] move = promptFirstMove(); // Reusing the method for simplicity; consider renaming it to something more general
            handleMove(move[0], move[1]);
            // Check for win/lose conditions
        }
    }

    private int[] promptFirstMove() {
        String input = scanner.nextLine().toUpperCase().trim();
        // Assuming valid input format, convert Base 36 to row and column indexes
        int row = Integer.parseInt(String.valueOf(input.charAt(0)), 36);
        int col = Integer.parseInt(String.valueOf(input.charAt(1)), 36);
        return new int[]{row, col};
    }

    private void handleMove(int row, int col) {
        // Placeholder for handling the move logic
        // This would include revealing the cell, checking if it's a mine, and updating game state
    }
}
