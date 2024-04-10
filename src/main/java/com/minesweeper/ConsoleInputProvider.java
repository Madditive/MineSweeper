package com.minesweeper;

import java.util.Scanner;

public class ConsoleInputProvider implements InputProvider {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getNextInput() {
        return scanner.nextLine();
    }

    @Override
    public int getNextInt() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the buffer to avoid infinite loop
            }
        }
    }
}

