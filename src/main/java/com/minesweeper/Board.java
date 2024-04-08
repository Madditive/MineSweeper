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
        // Initialize all cells
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public void placeMinesSafely(int firstRow, int firstCol) {
        Random random = new Random();
        int placedMines = 0;
        while (placedMines < mineCount) {
            int row = random.nextInt(height);
            int col = random.nextInt(width);

            // Ensure first cell and already placed mines are avoided
            if ((row != firstRow || col != firstCol) && !cells[row][col].isMine()) {
                cells[row][col].setMine(true);
                placedMines++;
            }
        }
        calculateAdjacentMines();
    }

    private void calculateAdjacentMines() {
        // Method to calculate and set the number of adjacent mines for each cell
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!cells[i][j].isMine()) {
                    int mineCount = countAdjacentMines(i, j);
                    cells[i][j].setAdjacentMines(mineCount);
                }
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        // Helper method to count adjacent mines for a given cell
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < height && j >= 0 && j < width && cells[i][j].isMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    public void revealCell(int row, int col) {
        // Method to reveal a cell at a specific location
        Cell cell = cells[row][col];
        if (cell.isRevealed() && !cell.isFlagged()) {
            cell.setRevealed(true);
            if (cell.isEmpty()) {
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

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public void displayBoard() {
        System.out.print("  "); // Initial spacing for row labels
        for (int j = 0; j < width; j++) {
            System.out.print(Integer.toString(j, 36).toUpperCase() + " "); // Convert to Base 36 and capitalize
        }
        System.out.println();

        for (int i = 0; i < height; i++) {
            System.out.print(Integer.toString(i, 36).toUpperCase() + " "); // Convert to Base 36 and capitalize

            for (int j = 0; j < width; j++) {
                System.out.print(cells[i][j].toString() + " ");
            }
            System.out.println(); // new Line at the end of each row
        }
    }
}
