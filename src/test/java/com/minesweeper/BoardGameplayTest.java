package com.minesweeper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardGameplayTest {

    private Board board;
    private final int width = 10;
    private final int height = 10;
    private final int mineCount = 20; // Adjust mine count as needed for testing

    @BeforeEach
    void setUp() {
        // Initialize a board with specified dimensions and mine count before each test
        board = new Board(width, height, mineCount);
        // Assuming placing mines safely and ensuring first cell and its adjacent are not mines for testing
        board.placeMinesSafely(5, 5); // Example: Ensuring the middle of the board is safe for initial reveal
    }

    @Test
    void revealCell_PropagatesCorrectly_WhenNoAdjacentMines() {
        // Reveal a cell with no adjacent mines
        board.revealCell(5, 5); // Assuming (5,5) has no adjacent mines

        // Verify that the cell and its adjacent cells are revealed
        assertTrue(board.getCell(5, 5).isRevealed(), "The cell should be revealed.");
        // Extend this assertion to verify adjacent cells are also revealed as expected

        // Example for directly adjacent cell - expand this logic based on your game's reveal propagation rules
        assertTrue(board.getCell(5, 6).isRevealed(), "Adjacent cell should also be revealed if there are no adjacent mines.");
    }

    @Test
    void flagCell_TogglesFlagStatusCorrectly() {
        // Toggle flag on a cell
        board.toggleFlagOnCell(5, 5);
        assertTrue(board.getCell(5, 5).isFlagged(), "The cell should be flagged after toggleFlagOnCell call.");

        // Toggle flag on the cell again to unflag
        board.toggleFlagOnCell(5, 5);

        // Verify the cell is no longer flagged
        assertFalse(board.getCell(5, 5).isFlagged(), "The cell should not be flagged after second toggleFlagOnCell call.");
    }
}
