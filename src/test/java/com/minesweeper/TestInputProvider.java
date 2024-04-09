package com.minesweeper;

import java.util.LinkedList;
import java.util.Queue;

public class TestInputProvider implements InputProvider {
    private final Queue<String> inputs = new LinkedList<>();

    public void addInput(String input) {
        inputs.add(input);
    }

    @Override
    public String getNextInput() {
        return inputs.poll(); // Retrieves and removes the head of this queue, or returns null if this queue is empty.
    }
}
