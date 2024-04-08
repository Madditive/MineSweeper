package com.minesweeper;

import java.util.ArrayList;
import java.util.List;
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

        // First, mark the selected cell and its neighbors as safe zones
        List<int[]> safeZones = new ArrayList<>();
        safeZones.add(new int[]{firstRow, firstCol});
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int safeRow = firstRow + i;
                int safeCol = firstCol + j;
                if (safeRow >= 0 && safeRow < height && safeCol >= 0 && safeCol < width) {
                    safeZones.add(new int[]{safeRow, safeCol});
                }
            }
        }

        // Then, place mines ensuring they don't land in the safe zones
        while (placedMines < mineCount) {
            int row = random.nextInt(height);
            int col = random.nextInt(width);

            boolean isSafeZone = safeZones.stream().anyMatch(zone -> zone[0] == row && zone[1] == col);
            if (!isSafeZone && !cells[row][col].isMine()) {
                cells[row][col] = CellFactory.createCell(true, 0);
                placedMines++;
            }
        }

        // After mines are placed, calculate the adjacent mines for all cells
        calculateAdjacentMines();
    }

    private void calculateAdjacentMines() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (!cells[row][col].isMine()) {
                    int mineCount = countAdjacentMines(row, col);
                    cells[row][col] = CellFactory.createCell(false, mineCount);
                }
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int adjRow = row + i;
                int adjCol = col + j;
                if (adjRow >= 0 && adjRow < height && adjCol >= 0 && adjCol < width && cells[adjRow][adjCol].isMine()) {
                    count++;
                }
            }
        }
        return count;
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
