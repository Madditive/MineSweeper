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
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print("."); // Display each cell as a "." to represent an unrevealed cell
            }
            System.out.println(); // new Line at the end of each row
        }
    }

}
