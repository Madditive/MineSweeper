package com.minesweeper;

public class Cell {
    private boolean isMine;
    private boolean isRevealed;
    private boolean isFlagged;
    private int adjacentMines; // Number of mines in adjacent cells

    public Cell() {
        this.isMine = false;
        this.isRevealed = false;
        this.isFlagged = false;
        this.adjacentMines = 0;
    }
    // Getter and setter for isMine
    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    // Getter and setter for isRevealed
    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    // Getter and setter for isFlagged
    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    // Getter and setter for adjacentMines
    public int getAdjacentMines() {
        return adjacentMines;
    }

    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }

    public boolean isEmpty() {
        return !this.isMine && this.adjacentMines == 0;
    }

    // Method to display the cell's current state
    public String toString() {
        if (isRevealed) {
            return isMine ? "*" : Integer.toString(adjacentMines);
        } else if (isFlagged) {
            return "F";
        } else {
            return "#";
        }
    }
}
