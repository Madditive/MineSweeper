package com.minesweeper;

import java.util.Scanner;

public class Game {
    private final Board board;
    private final InputProvider inputProvider;
    private boolean isGameOver = false;
    private final int width;
    private final int height;
    private final int mineCount;

    public Game(int width, int height, int mineCount, InputProvider inputProvider) {
        this.width = width;
        this.height = height;
        this.mineCount = mineCount;
        this.inputProvider = inputProvider;
        this.board = new Board(width, height, mineCount);
    }

    public void start() {
        System.out.println("Welcome to Minesweeper!");

        // Display the initial empty board
        board.displayBoard();

        // Prompt user for the first move, ensuring it is safe
        System.out.println("Enter row and column for the first move (e.g., 2B):");
        handleUserMove(true); // `true` signifies the first move

        // Main game loop
        while (!isGameOver) {
            board.displayBoard();
            System.out.println("Choose an action:");
            System.out.println("R: Reveal a cell (e.g., R2B)");
            System.out.println("F: Flag/Unflag a cell (e.g., F2B)");
            handleUserMove(false);
        }

        if(isGameOver) {
            board.displayBoard();
            // Determine if the player won or lost
            if (board.countRevealedCells() == (width * height - mineCount)) {
                System.out.println("Congratulations! You've won the game!");
            } else {
                System.out.println("Game Over. You hit a mine.");
            }
            System.out.println("Thank you for playing!");
        }
    }

    private void handleUserMove(boolean isFirstMove) {
        String input = inputProvider.getNextInput().toUpperCase().trim();

        if (isFirstMove && input.length() != 2) {
            System.out.println("Invalid input. Please enter a valid row and column (e.g., 2B).");
            handleUserMove(true); // Recursive call to handle the first move again
            return;
        }

        if (!isFirstMove && !input.matches("[RF][0-9A-Z][0-9A-Z]")) {
            System.out.println("Invalid action. Please use 'R' to reveal or 'F' to flag a cell (e.g., R2B).");
            return;
        }

        // Extracting row and column from the input
        int row, col;
        try {
            // Adjust the logic for base 36 conversion.
            String position = isFirstMove ? input : input.substring(1);
            row = Integer.parseInt(String.valueOf(position.charAt(0)), 36); // This remains the same since row '2' should map directly.

            // For columns, correctly interpret letters and digits for zero-based indexing
            col = Integer.parseInt(position.substring(1), 36); // Correctly handle 'B' as 11 in zero-based indexing

            // Check if the selected cell is within the board's bounds
            if (row >= height || col >= width || row < 0 || col < 0) {
                System.out.println("Selected cell is out of the board's bounds. Please select a valid cell.");
                handleUserMove(isFirstMove);
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid position. Please enter a valid row and column (e.g., 2B).");
            handleUserMove(isFirstMove);
            return;
        }

        // Action handling remains the same
        char actionType = isFirstMove ? 'R' : input.charAt(0);
        if (actionType == 'R') {
            if (isFirstMove) {
                board.placeMinesSafely(row, col);
            }
            board.revealCell(row, col);
            if (board.getCell(row, col).isMine()) {
                isGameOver = true;
            }
        } else if (actionType == 'F' && !isFirstMove) {
            board.toggleFlagOnCell(row, col);
        }

        checkWinCondition();
    }

    private void checkWinCondition() {
        // Win condition: All non-mine cells are revealed
        if (board.countRevealedCells() + mineCount == (width * height)) {
            isGameOver = true;
        }
    }

    public double getWidth() {
        return  this.width;
    }

    public double getHeight() {
        return  this.height;
    }

    public double getMineCount() {
        return  this.mineCount;
    }

    public Board getBoard() {
        return this.board;
    }

    public boolean checkWin() {
        return board.checkWinCondition();
    }

    public void revealCell(int row, int col) {
        board.revealCell(row, col);
        if (board.getCell(row, col).isMine()) {
            // Set the game over state here
            this.isGameOver = true;
        }
    }

    public boolean isGameOver() {
        return this.isGameOver;
    }

    public boolean isWin() {
        // Implement logic to determine if the game is won
        return !isGameOver && board.checkWinCondition();
    }
}
