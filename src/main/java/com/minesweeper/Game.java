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
        int[] firstMove = promptForMove();
        board.placeMinesSafely(firstMove[0], firstMove[1]);
        revealCell(firstMove[0], firstMove[1]);

        // Main game loop
        while (!isGameOver) {
            board.displayBoard();
            System.out.println("Enter row and column to reveal (e.g., 2B):");
            int[] move = promptForMove();
            revealCell(move[0], move[1]);

            // Check for win/lose conditions
        }
    }

    private int[] promptForMove() {
        String input = scanner.nextLine().toUpperCase().trim();
        int row = Integer.parseInt(String.valueOf(input.charAt(0)), 36);
        int col = Integer.parseInt(String.valueOf(input.charAt(1)), 36);
        return new int[]{row, col};
    }

    private void revealCell(int row, int col) {
        // Check if the selected cell is a mine
        if (board.getCell(row, col).isMine()) {
            System.out.println("Game Over! You hit a mine.");
            isGameOver = true;
            // Optionally reveal all cells or just the mines here
            return;
        }
    }
}
