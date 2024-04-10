package com.minesweeper;

public class GameController {
    private Game game;
    private final InputProvider inputProvider;

    public GameController(InputProvider inputProvider) {
        this.inputProvider = inputProvider;
    }

    public void startGame() {
        System.out.println("Welcome to Minesweeper!");
        selectDifficulty();
        game.start(); // Assuming game.start() manages the game loop and state transitions
    }

    private void selectDifficulty() {
        System.out.println("Select difficulty:");
        System.out.println("1: Easy (8x8 with 10 mines)");
        System.out.println("2: Intermediate (16x16 with 40 mines)");
        System.out.println("3: Expert (30x16 with 99 mines)");

        int difficultyChoice = inputProvider.getNextInt(); // Ensure InputProvider has method getNextInt()

        switch (difficultyChoice) {
            case 1:
                game = new Game(8, 8, 10, inputProvider);
                break;
            case 2:
                game = new Game(16, 16, 40, inputProvider);
                break;
            case 3:
                game = new Game(30, 16, 99, inputProvider);
                break;
            default:
                System.out.println("Invalid selection. Defaulting to Easy.");
                game = new Game(8, 8, 10, inputProvider);
                break;
        }
    }
}
