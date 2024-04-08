package com.minesweeper;

public abstract class Cell {
    protected boolean isRevealed = false;
    protected boolean isFlagged = false;

    // Common method to flag a cell. Subclasses can use this directly if no special behavior is needed.
    public void toggleFlag() {
        if (!isRevealed) {
            isFlagged = !isFlagged;
        }
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public boolean isFlagged() {
        return isFlagged;
    }


    public abstract String toString();
    public abstract boolean isMine();
    public abstract void setAdjacentMines(int adjacentMines);
}



