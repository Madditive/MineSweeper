package com.minesweeper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BombCellTest {

    @Test
    public void testIsMineReturnsTrueForBombCell() {
        Cell cell = new BombCell();
        boolean result = cell.isMine();
        assertTrue(result, "BombCell should return true for isMine()");
    }

    @Test
    public void testToggleFlagStatus() {
        Cell cell = new BombCell();
        // Initially, the cell should not be flagged
        assertFalse(cell.isFlagged(), "Cell should initially not be flagged");

        cell.toggleFlag();
        // Assert - Now the cell should be flagged
        assertTrue(cell.isFlagged(), "Cell should be flagged after toggleFlag() call");

        cell.toggleFlag();
        assertFalse(cell.isFlagged(), "Cell should not be flagged after second toggleFlag() call");
    }

    @Test
    public void testMarkAsRevealed() {
        Cell cell = new BombCell();

        // Initially, the cell should not be revealed
        assertFalse(cell.isRevealed(), "Cell should initially not be revealed");

        cell.setRevealed(true);
        // Assert - Now the cell should be revealed
        assertTrue(cell.isRevealed(), "Cell should be revealed after setRevealed(true) call");
    }
}
