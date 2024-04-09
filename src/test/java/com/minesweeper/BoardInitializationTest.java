package com.minesweeper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardInitializationTest {

    private Board board;
    private final int width = 10;
    private final int height = 10;
    private final int mineCount = 15; // Arbitrary number for initialization

    @BeforeEach
    void setUp() {
        // Initialize a new Board with specified dimensions and mine count before each test
        board = new Board(width, height, mineCount);
    }

    @Test
    void boardSize_InitializesCorrectly() {
        // Verify the board's dimensions are correctly initialized
        assertEquals(height, board.getHeight(), "Board height should match initialization parameter.");
        assertEquals(width, board.getWidth(), "Board width should match initialization parameter.");
    }

    @Test
    void initialCellStatus_AllCellsNotRevealedNotFlagged() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell cell = board.getCell(i, j);
                assertFalse(cell.isRevealed(), "All cells should initially not be revealed.");
                assertFalse(cell.isFlagged(), "All cells should initially not be flagged.");
            }
        }
    }
}
