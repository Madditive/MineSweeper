package com.minesweeper;

public class Board {
    private Cell[][] cells;
    private int width;
    private int height;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[height][width];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell();
            }
        }
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
                System.out.print("# "); // Display each cell as a "#" to represent an unrevealed cell with spacing
            }
            System.out.println(); // new Line at the end of each row
        }
    }

}
