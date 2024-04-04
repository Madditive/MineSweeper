package com.minesweeper;

public class Game {
    private Board board;

    public Game(int width, int height) {
        this.board = new Board(width, height);
    }

    public void start() {
        System.out.println("Welcome to Minesweeper!");
        board.displayBoard();
    }
}
