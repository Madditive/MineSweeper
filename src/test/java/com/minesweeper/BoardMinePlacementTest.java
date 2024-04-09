package com.minesweeper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;

public class BoardMinePlacementTest {

    private Board board;
    private final int width = 10;
    private final int height = 10;
    private final int mineCount = 20; // Example mine count for testing

    @BeforeEach
    void setUp() {
        // Initialize board with specified dimensions and mine count for each test
        board = new Board(width, height, mineCount);
    }

    @Test
    void minesCount_AfterPlacing_IsCorrect() {
        // The first move is simulated to ensure a safe start
        board.placeMinesSafely(0, 0); // Assuming the first move is at (0,0)

        // Count the total number of mines placed on the board
        long actualMineCount = IntStream.range(0, height)
                .mapToLong(i -> IntStream.range(0, width)
                        .filter(j -> board.getCell(i, j).isMine())
                        .count())
                .sum();

        assertEquals(mineCount, actualMineCount, "The number of mines placed on the board should match the specified mine count.");
    }

    @Test
    void firstMove_IsAlwaysSafe() {
        // Simulate the first move
        board.placeMinesSafely(0, 0); // Assuming the first move is at (0,0)

        // Check the selected cell and its adjacent cells are not mines
        assertFalse(board.getCell(0, 0).isMine(), "The first selected cell should not be a mine.");
        assertAll("All adjacent cells to the first move should be safe",
                () -> assertFalse(board.getCell(0, 1).isMine(), "(0,1) should not be a mine."),
                () -> assertFalse(board.getCell(1, 0).isMine(), "(1,0) should not be a mine."),
                () -> assertFalse(board.getCell(1, 1).isMine(), "(1,1) should not be a mine.")
        );
    }
}
