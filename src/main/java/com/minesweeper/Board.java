package com.minesweeper;

import java.util.Random;

public class Board {
    private final Cell[][] cells;
    private final int width;
    private final int height;
    private final int mineCount;

    public Board(int width, int height, int mineCount) {
        this.width = width;
        this.height = height;
        this.mineCount = mineCount;
        this.cells = new Cell[height][width];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Initially, create all cells as NotBombCell with 0 adjacent mines
                cells[i][j] = CellFactory.createCell(false, 0);
            }
        }
    }

    public void placeMinesSafely(int firstRow, int firstCol) {
        Random random = new Random();
        int placedMines = 0;

        while (placedMines < mineCount) {
            int row = random.nextInt(height);
            int col = random.nextInt(width);

            // Ensure the first selected cell is not a mine and is not already a BombCell
            if ((row == firstRow && col == firstCol) || cells[row][col].isMine()) {
                continue;
            }

            // Replace the current cell with a BombCell
            cells[row][col] = CellFactory.createCell(true, 0);
            placedMines++;

            // Update adjacent cells' mine counts
            updateAdjacentCells(row, col);
        }
    }

    private void updateAdjacentCells(int bombRow, int bombCol) {
        for (int row = Math.max(0, bombRow - 1); row <= Math.min(bombRow + 1, height - 1); row++) {
            for (int col = Math.max(0, bombCol - 1); col <= Math.min(bombCol + 1, width - 1); col++) {
                if (!(cells[row][col].isMine())) {
                    int currentMines = cells[row][col] instanceof NotBombCell ? ((NotBombCell) cells[row][col]).getAdjacentMines() : 0;
                    cells[row][col] = CellFactory.createCell(false, currentMines + 1);
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
