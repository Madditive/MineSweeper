package com.minesweeper;

public class CellFactory {

    /**
     * Creates a cell based on whether it is a mine.
     *
     * @param isMine Whether the cell to be created is a mine.
     * @param adjacentMines The number of adjacent mines, relevant for NotBombCell.
     * @return A new Cell instance, either a BombCell or NotBombCell.
     */
    public static Cell createCell(boolean isMine, int adjacentMines) {
        if (isMine) {
            return new BombCell();
        } else {
            return new NotBombCell(adjacentMines);
        }
    }
}
