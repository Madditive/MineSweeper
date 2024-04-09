package com.minesweeper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotBombCellTest {

    @Test
    public void testIsMineReturnsFalseForNotBombCell() {
        Cell cell = new NotBombCell(0); // Assuming adjacent mines are set to 0 for simplicity
        assertFalse(cell.isMine(), "NotBombCell should return false for isMine()");
    }

    @Test
    public void testSetAndGetAdjacentMines() {
        // Create a NotBombCell with a specific number of adjacent mines
        int adjacentMines = 3; // Example number of adjacent mines
        NotBombCell cell = new NotBombCell(0); // Initialize with 0 for simplicity

        // Set the number of adjacent mines
        cell.setAdjacentMines(adjacentMines);

        // Verify that getAdjacentMines() returns the correct number
        assertEquals(adjacentMines, cell.getAdjacentMines(), "NotBombCell should return the correct number of adjacent mines");
    }

    @Test
    public void testToggleFlagStatus() {
        Cell cell = new NotBombCell(0);

        // Initially not flagged
        assertFalse(cell.isFlagged(), "Cell should initially not be flagged");

        // Toggle flag status to true
        cell.toggleFlag();
        assertTrue(cell.isFlagged(), "Cell should be flagged after toggleFlag() call");

        // Toggle the flag status back to false
        cell.toggleFlag();
        assertFalse(cell.isFlagged(), "Cell should not be flagged after second toggleFlag() call");
    }

    @Test
    public void testMarkAsRevealed() {
        Cell cell = new NotBombCell(0);
        // Mark the cell as revealed
        cell.setRevealed(true);
        assertTrue(cell.isRevealed(), "Cell should be revealed after setRevealed(true) call");
    }
}
