package com.minesweeper;

public class NotBombCell extends Cell {
    private int adjacentMines;

    public NotBombCell(int adjacentMines) {
        super();  // Call to the superclass constructor, if needed.
        this.adjacentMines = adjacentMines;  // Initialize with no adjacent mines.
    }

    @Override
    public boolean isMine() {
        return false;
    }

    @Override
    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }


    public int getAdjacentMines() {
        return this.adjacentMines;
    }

    @Override
    public String toString() {
        if (isRevealed) {
            return adjacentMines > 0 ? String.valueOf(adjacentMines) : " ";
        } else if (isFlagged) {
            return "F";
        } else {
            return "#";
        }
    }
}
