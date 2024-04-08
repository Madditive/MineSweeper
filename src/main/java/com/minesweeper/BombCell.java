package com.minesweeper;

public class BombCell extends Cell {

    @Override
    public boolean isMine() {
        return true;
    }

    @Override
    public void setAdjacentMines(int adjacentMines) {
        // No operation, or throw an exception, because it doesn't make sense for a bomb cell
        throw new UnsupportedOperationException("Cannot set adjacent mines on a bomb cell.");
    }

    @Override
    public String toString() {
        if (isRevealed) {
            return "*";  // Assume you want to show the bomb when revealed.
        } else if (isFlagged) {
            return "F";
        } else {
            return "#";
        }
    }




}

