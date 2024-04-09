package com.minesweeper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CellFactoryTest {

    @Test
    public void createBombCell_VerifyProperties() {
        Cell bombCell = CellFactory.createCell(true, 0);

        // Verify that the created cell is a BombCell and has correct properties
        assertTrue(bombCell instanceof BombCell, "Should create an instance of BombCell");
        assertTrue(bombCell.isMine(), "BombCell should return true for isMine()");
    }

    @Test
    public void createNotBombCell_VerifyProperties() {
        int expectedAdjacentMines = 3;

        // Create a NotBombCell via the factory with the specified number of adjacent mines
        Cell notBombCell = CellFactory.createCell(false, expectedAdjacentMines);
        assertTrue(notBombCell instanceof NotBombCell, "Should create an instance of NotBombCell");
        assertFalse(notBombCell.isMine(), "NotBombCell should return false for isMine()");
        assertEquals(expectedAdjacentMines, ((NotBombCell) notBombCell).getAdjacentMines(),
                "NotBombCell should have the correct number of adjacent mines");
    }
}
