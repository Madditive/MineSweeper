package com.minesweeper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameDifficultySelectionTest {

    @Test
    void validSelection_AppliesCorrectBoardSizeAndMineCount() {
        // Test each difficulty setting

        // Easy
        Game easyGame = new Game("Easy");
        assertEquals(8, easyGame.getWidth(), "Easy difficulty should have width of 8.");
        assertEquals(8, easyGame.getHeight(), "Easy difficulty should have height of 8.");
        assertEquals(10, easyGame.getMineCount(), "Easy difficulty should have 10 mines.");

        // Intermediate
        Game intermediateGame = new Game("Intermediate");
        assertEquals(16, intermediateGame.getWidth(), "Intermediate difficulty should have width of 16.");
        assertEquals(16, intermediateGame.getHeight(), "Intermediate difficulty should have height of 16.");
        assertEquals(40, intermediateGame.getMineCount(), "Intermediate difficulty should have 40 mines.");

        // Expert
        Game expertGame = new Game("Expert");
        assertEquals(30, expertGame.getWidth(), "Expert difficulty should have width of 30.");
        assertEquals(16, expertGame.getHeight(), "Expert difficulty should have height of 16.");
        assertEquals(99, expertGame.getMineCount(), "Expert difficulty should have 99 mines.");
    }

    @Test
    void invalidSelection_DefaultsToPredefinedDifficulty() {
        // Assuming "Easy" is the default difficulty for invalid selections
        Game invalidGame = new Game("Invalid");
        assertEquals(8, invalidGame.getWidth(), "Invalid difficulty selection should default to Easy difficulty width.");
        assertEquals(8, invalidGame.getHeight(), "Invalid difficulty selection should default to Easy difficulty height.");
        assertEquals(10, invalidGame.getMineCount(), "Invalid difficulty selection should default to Easy difficulty mine count.");
    }
}
