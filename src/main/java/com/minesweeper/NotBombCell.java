package com.minesweeper;

public class NotBombCell extends Cell {
    private int adjacentMines;

    public NotBombCell(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }

    public NotBombCell() {
        this.adjacentMines = 0;
    }

    public int getAdjacentMines() {
        return adjacentMines;
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

    @Override
    public boolean isMine() {
        return false;
    }

    @Override
    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }

}
