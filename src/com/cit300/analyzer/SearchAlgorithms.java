package com.cit300.analyzer;

public class SearchAlgorithms {
    private final PerformanceTracker performanceTracker;

    public SearchAlgorithms(PerformanceTracker performanceTracker) {
        if (performanceTracker == null) {
            throw new IllegalArgumentException("Performance tracker cannot be null.");
        }
        this.performanceTracker = performanceTracker;
    }

    /**
     * Searches from the first value to the last and returns the matching index,
     * or -1 when the target is not present.
     */
    public int linearSearch(int[] values, int target) {
        if (values == null) {
            throw new IllegalArgumentException("Values cannot be null.");
        }

        long comparisons = 0;
        long startTime = System.nanoTime();
        int index = -1;

        for (int current = 0; current < values.length; current++) {
            comparisons++;
            if (values[current] == target) {
                index = current;
                break;
            }
        }

        long elapsedNanos = System.nanoTime() - startTime;
        performanceTracker.record("Linear Search", values.length, comparisons,
                elapsedNanos, describeResult(target, index, comparisons));
        return index;
    }

    /**
     * Searches sorted input without changing it. Throws an exception if the
     * values are not in ascending order.
     *
     * @return the matching index, or -1 when the target is not present
     */
    public int binarySearch(int[] values, int target) {
        if (values == null) {
            throw new IllegalArgumentException("Values cannot be null.");
        }
        if (!isSorted(values)) {
            throw new IllegalArgumentException(
                    "Binary search requires values sorted in ascending order.");
        }

        long comparisons = 0;
        long startTime = System.nanoTime();
        int low = 0;
        int high = values.length - 1;
        int index = -1;

        while (low <= high) {
            int middle = low + (high - low) / 2;

            comparisons++;
            if (values[middle] == target) {
                index = middle;
                break;
            }

            comparisons++;
            if (target < values[middle]) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }

        long elapsedNanos = System.nanoTime() - startTime;
        performanceTracker.record("Binary Search", values.length, comparisons,
                elapsedNanos, describeResult(target, index, comparisons));
        return index;
    }

    private boolean isSorted(int[] values) {
        for (int index = 1; index < values.length; index++) {
            if (values[index - 1] > values[index]) {
                return false;
            }
        }
        return true;
    }

    private String describeResult(int target, int index, long comparisons) {
        if (index >= 0) {
            return "target " + target + " found at index " + index
                    + "; comparisons: " + comparisons;
        }
        return "target " + target + " was not found; comparisons: "
                + comparisons;
    }
}
