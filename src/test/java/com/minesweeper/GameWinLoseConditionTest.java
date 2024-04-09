package com.minesweeper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameWinLoseConditionTest {

    private Game game;
    private Board board;
    private TestInputProvider inputProvider;

    @BeforeEach
    void setUp() {
        game = new Game("EASY", inputProvider); // Assuming "EASY" is mapped to a small, manageable board for testing
        board = game.getBoard(); // Assuming you have a method to retrieve the board from the game
        // Further setup might be needed to configure the board for specific test scenarios
    }

    @Test
    void winCondition_AllNonMineCellsRevealed() {
        prepareBoardForWin(game.getBoard());
        revealAllNonMineCells(game.getBoard());
        assertTrue(game.checkWin(), "Game should recognize a win when all non-mine cells are revealed.");    }

    private void prepareBoardForWin(Board board) {
        for (int row = 0; row < board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                Cell cell = board.getCell(row, col);
                if (cell instanceof BombCell) {
                    // If it's a bomb cell, replace it with a non-bomb cell with 0 adjacent mines
                    board.setCell(row, col, new NotBombCell(0));
                } else if (cell instanceof NotBombCell) {
                    // Reset adjacent mines count to 0 or an appropriate number based on your win scenario
                    ((NotBombCell) cell).setAdjacentMines(0);
                }
            }
        }
        int mineRow = board.getHeight() - 1;
        int mineCol = board.getWidth() - 1;
        board.setCell(mineRow, mineCol, new BombCell());
    }

    private void revealAllNonMineCells(Board board) {
        for (int row = 0; row < board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                Cell cell = board.getCell(row, col);
                if (!cell.isMine()) {
                    cell.setRevealed(true);
                }
            }
        }
    }

    @Test
    void loseCondition_RevealAMine() {
        int knownMineRow = 0;
        int knownMineColumn = 0;

        // Ensure the board is clear except for one mine at the known location
        board.clearBoard();
        board.setCell(knownMineRow, knownMineColumn, new BombCell());
        game.revealCell(knownMineRow, knownMineColumn);

        assertTrue(game.isGameOver(), "Game should be over after revealing a mine.");
        assertFalse(game.isWin(), "Game should not be in a win state after a mine is revealed.");
    }
}
