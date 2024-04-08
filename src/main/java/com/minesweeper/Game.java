package com.minesweeper;

import java.util.Scanner;

public class Game {
    private Board board;
    private final Scanner scanner = new Scanner(System.in);
    private boolean isGameOver = false;
    private final int width;
    private final int height;
    private final int mineCount;

    public Game(int width, int height, int mineCount) {
        this.width = width;
        this.height = height;
        this.mineCount = mineCount;
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
            System.out.println("1. Reveal a cell (e.g., R2B)");
            System.out.println("2. Flag/Unflag a cell (e.g., F2B)");
            handleUserMove(false);
        }

        if(isGameOver) {
            board.displayBoard();
            System.out.println("Game Over. Thank you for playing!");
        }
    }

    private void handleUserMove(boolean isFirstMove) {
        String action = scanner.nextLine().toUpperCase().trim();
        char actionType = action.charAt(0);
        int row = Integer.parseInt(action.substring(1, 2), 36);
        int col = Integer.parseInt(action.substring(2), 36);

        switch (actionType) {
            case 'R':
                if (isFirstMove) {
                    board.placeMinesSafely(row, col);
                }
                board.revealCell(row, col);
                if (board.getCell(row, col).isMine()) {
                    isGameOver = true;
                }
                break;
            case 'F':
                board.toggleFlagOnCell(row, col);
                break;
            default:
                System.out.println("Invalid action. Please try again.");
                break;
        }

        checkWinCondition();
    }

    private void checkWinCondition() {
        // Win condition: All non-mine cells are revealed
        if (board.countRevealedCells() == (width * height - mineCount)) {
            System.out.println("Congratulations! You've won the game!");
            isGameOver = true;
        }
    }
}
