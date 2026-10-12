package com.cit300.analyzer;

/**
 * A simple integer stack using an array.
 * It follows the LIFO rule: the last item pushed is the first item removed.
 */
public class IntStack {
    private static final int DEFAULT_CAPACITY = 10;

    private final int[] stack;
    private int top;
    private final PerformanceTracker performanceTracker;

    public IntStack() {
        this(DEFAULT_CAPACITY);
    }

    public IntStack(int capacity) {
        this(capacity, null);
    }

    public IntStack(PerformanceTracker performanceTracker) {
        this(DEFAULT_CAPACITY, performanceTracker);
    }

    public IntStack(int capacity, PerformanceTracker performanceTracker) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Stack capacity must be greater than zero.");
        }
        this.stack = new int[capacity];
        this.top = -1;
        this.performanceTracker = performanceTracker;
    }

    /**
     * Pushes a value onto the top of the stack.
     * Returns true if the push was successful, false if the stack is full.
     */
    public boolean push(int value) {
        long startTime = System.nanoTime();

        if (isFull()) {
            recordMeasurement("PUSH", size(), 0, System.nanoTime() - startTime,
                    "Stack is full. Value was not added.");
            return false;
        }

        top++;
        stack[top] = value;

        recordMeasurement("PUSH", size(), top + 1, System.nanoTime() - startTime,
                "Pushed value: " + value);
        return true;
    }

    /**
     * Removes and returns the top element.
     * If the stack is empty, it safely returns -1 instead of crashing.
     */
    public int pop() {
        long startTime = System.nanoTime();

        if (isEmpty()) {
            recordMeasurement("POP", size(), 0, System.nanoTime() - startTime,
                    "Stack is empty. No value removed.");
            return -1;
        }

        int value = stack[top];
        top--;

        recordMeasurement("POP", size(), top + 1, System.nanoTime() - startTime,
                "Popped value: " + value);
        return value;
    }

    /**
     * Returns the top element without removing it.
     * If the stack is empty, it safely returns -1.
     */
    public int peek() {
        long startTime = System.nanoTime();

        if (isEmpty()) {
            recordMeasurement("PEEK", size(), 0, System.nanoTime() - startTime,
                    "Stack is empty. No top value.");
            return -1;
        }

        int value = stack[top];
        recordMeasurement("PEEK", size(), top + 1, System.nanoTime() - startTime,
                "Top value: " + value);
        return value;
    }

    /**
     * Returns a readable description of the stack contents.
     * The top item is shown at the end of the list.
     */
    public String display() {
        long startTime = System.nanoTime();
        String result;

        if (isEmpty()) {
            result = "Stack is empty.";
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append("Stack (top at end): ");
            for (int index = 0; index <= top; index++) {
                if (index > 0) {
                    builder.append(", ");
                }
                builder.append(stack[index]);
            }
            result = builder.toString();
        }

        recordMeasurement("DISPLAY", size(), size(), System.nanoTime() - startTime, result);
        System.out.println(result);
        return result;
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top < 0;
    }

    public boolean isFull() {
        return top >= stack.length - 1;
    }

    public int capacity() {
        return stack.length;
    }

    public int topValue() {
        return peek();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Stack is empty.";
        }

        StringBuilder builder = new StringBuilder();
        for (int index = 0; index <= top; index++) {
            if (index > 0) {
                builder.append(", ");
            }
            builder.append(stack[index]);
        }
        return builder.toString();
    }

    private void recordMeasurement(String operationName, int inputSize, long steps,
                                  long elapsedNanos, String details) {
        if (performanceTracker != null) {
            performanceTracker.record(operationName, inputSize, steps, elapsedNanos, details);
        }
    }
}
