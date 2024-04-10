package com.minesweeper;


public class Main {
    public static void main(String[] args) {
        InputProvider inputProvider = new ConsoleInputProvider();
        GameController gameController = new GameController(inputProvider);
        gameController.startGame();
    }
}