package com.minesweeper;

import java.util.Random;

public class Board {
    private Cell[][] cells;
    private int width;
    private int height;
    private int mineCount;

    public Board(int width, int height, int mineCount) {
        this.width = width;
        this.height = height;
        this.mineCount = mineCount;
        this.cells = new Cell[height][width];
        initializeBoard();
    }

    private void initializeBoard() {
        // Initially, fill the board with NotBombCells.
        // The number of adjacent mines will be set later.
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new NotBombCell(0); // Defaulting to no adjacent mines
            }
        }
    }

    public void placeMinesSafely(int firstRow, int firstCol) {
        Random random = new Random();
        int placedMines = 0;
        while (placedMines < mineCount) {
            int row = random.nextInt(height);
            int col = random.nextInt(width);

            // Ensure the first cell and its adjacent cells do not contain mines
            if ((row != firstRow || col != firstCol) && !(cells[row][col] instanceof BombCell)) {
                // Replace a NotBombCell with a BombCell
                cells[row][col] = new BombCell();
                placedMines++;
                // Update adjacent mines count for surrounding cells
                updateAdjacentCells(row, col);
            }
        }
    }

    private void updateAdjacentCells(int bombRow, int bombCol) {
        for (int i = Math.max(0, bombRow - 1); i <= Math.min(bombRow + 1, height - 1); i++) {
            for (int j = Math.max(0, bombCol - 1); j <= Math.min(bombCol + 1, width - 1); j++) {
                if (!(cells[i][j] instanceof BombCell)) {
                    NotBombCell cell = (NotBombCell) cells[i][j];
                    cell.setAdjacentMines(cell.getAdjacentMines() + 1);
                }
            }
        }
    }

    public void revealCell(int row, int col) {
        // Method to reveal a cell at a specific location
        Cell cell = cells[row][col];
        if (!cell.isRevealed() && !cell.isFlagged()) {
            cell.setRevealed(true);
            if (cell instanceof NotBombCell && ((NotBombCell) cell).getAdjacentMines() == 0) {
                revealAdjacentCells(row, col);
            }
        }
    }

    private void revealAdjacentCells(int row, int col) {
        // Helper method to recursively reveal adjacent cells
        for (int i = Math.max(0, row - 1); i <= Math.min(row + 1, height - 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(col + 1, width - 1); j++) {
                // Only reveal the cell if it hasn't already been revealed
                if (!cells[i][j].isRevealed()) {
                    revealCell(i, j); // This recursion will only continue for empty cells
                }
            }
        }
    }

    public void toggleFlagOnCell(int row, int col) {
        cells[row][col].toggleFlag();
    }

    public void displayBoard() {
        // Print column labels (0-9 then A-Z for simplicity)
        System.out.print("  "); // Initial spacing for alignment
        for (int j = 0; j < width; j++) {
            System.out.print(Integer.toString(j, 36).toUpperCase() + " "); // Convert to Base 36 and capitalize
        }
        System.out.println();

        for (int i = 0; i < height; i++) {
            // Print row labels
            System.out.print(Integer.toString(i, 36).toUpperCase() + " "); // Convert to Base 36 and capitalize
            for (int j = 0; j < width; j++) {
                System.out.print(cells[i][j].toString() + " ");
            }
            System.out.println(); // new Line at the end of each row
        }
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public int countRevealedCells() {
        int revealedCells = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cells[i][j].isRevealed()) {
                    revealedCells++;
                }
            }
        }
        return revealedCells;
    }
}
