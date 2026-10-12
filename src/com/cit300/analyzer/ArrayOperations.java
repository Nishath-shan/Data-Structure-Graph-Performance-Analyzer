package com.cit300.analyzer;

import java.util.ArrayList;
import java.util.List;

public class ArrayOperations {
    private final List<Integer> values = new ArrayList<>();
    private final PerformanceTracker performanceTracker;

    public ArrayOperations(PerformanceTracker performanceTracker) {
        if (performanceTracker == null) {
            throw new IllegalArgumentException("Performance tracker cannot be null.");
        }
        this.performanceTracker = performanceTracker;
    }

    /**
     * Adds a value to the end of the collection.
     */
    public void insert(int value) {
        insert(values.size(), value);
    }

    /**
     * Inserts a value at an index from zero through the current size.
     */
    public void insert(int index, int value) {
        if (index < 0 || index > values.size()) {
            throw new IndexOutOfBoundsException(
                    "Insert index must be between 0 and " + values.size() + ".");
        }

        int inputSize = values.size();
        int elementsShifted = inputSize - index;
        long startTime = System.nanoTime();
        values.add(index, value);
        long elapsedNanos = System.nanoTime() - startTime;

        performanceTracker.record("Array INSERT", inputSize, elementsShifted,
                elapsedNanos, "inserted " + value + " at index " + index
                        + "; elements shifted: " + elementsShifted);
    }

    /**
     * Removes the first matching value, preserving the order of the others.
     *
     * @return true if a value was removed, or false if it was not present
     */
    public boolean delete(int value) {
        int inputSize = values.size();
        long comparisons = 0;
        long startTime = System.nanoTime();
        int index = -1;

        for (int current = 0; current < values.size(); current++) {
            comparisons++;
            if (values.get(current) == value) {
                index = current;
                break;
            }
        }

        int elementsShifted = 0;
        if (index >= 0) {
            elementsShifted = values.size() - index - 1;
            values.remove(index);
        }
        long elapsedNanos = System.nanoTime() - startTime;
        boolean deleted = index >= 0;

        performanceTracker.record("Array DELETE", inputSize,
                comparisons + elementsShifted, elapsedNanos,
                deleted
                        ? "deleted " + value + " at index " + index
                                + "; comparisons: " + comparisons
                                + ", elements shifted: " + elementsShifted
                        : "value " + value + " was not found; comparisons: "
                                + comparisons);
        return deleted;
    }

    /**
     * Returns the index of the first matching value, or -1 if absent.
     */
    public int search(int value) {
        int inputSize = values.size();
        long comparisons = 0;
        long startTime = System.nanoTime();
        int index = -1;

        for (int current = 0; current < values.size(); current++) {
            comparisons++;
            if (values.get(current) == value) {
                index = current;
                break;
            }
        }

        long elapsedNanos = System.nanoTime() - startTime;
        performanceTracker.record("Array SEARCH", inputSize, comparisons,
                elapsedNanos, index >= 0
                        ? "value " + value + " found at index " + index
                                + "; comparisons: " + comparisons
                        : "value " + value + " was not found; comparisons: "
                                + comparisons);
        return index;
    }

    /**
     * Returns the current values in order, or a clear message when empty.
     */
    public String display() {
        int inputSize = values.size();
        long startTime = System.nanoTime();
        String output = values.isEmpty() ? "Array is empty." : values.toString();
        long elapsedNanos = System.nanoTime() - startTime;

        performanceTracker.record("Array DISPLAY", inputSize, inputSize,
                elapsedNanos, values.isEmpty()
                        ? "collection is empty"
                        : "displayed " + inputSize + " values");
        return output;
    }
}
