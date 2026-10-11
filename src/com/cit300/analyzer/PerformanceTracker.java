package com.cit300.analyzer;

import java.util.ArrayList;
import java.util.List;

public class PerformanceTracker {
    private final List<Measurement> measurements = new ArrayList<>();

    /**
     * Records a measurement produced by a completed operation.
     *
     * @param operationName name of the operation
     * @param inputSize     size of the operation's input
     * @param steps         meaningful steps or operations counted
     * @param elapsedNanos  elapsed time in nanoseconds, or -1 if unavailable
     * @param details       short description of the result
     */
    public void record(String operationName, int inputSize, long steps,
                       long elapsedNanos, String details) {
        if (operationName == null || operationName.trim().isEmpty()) {
            throw new IllegalArgumentException("Operation name cannot be blank.");
        }
        if (inputSize < 0) {
            throw new IllegalArgumentException("Input size cannot be negative.");
        }
        if (steps < 0) {
            throw new IllegalArgumentException("Steps cannot be negative.");
        }
        if (elapsedNanos < -1) {
            throw new IllegalArgumentException("Elapsed time must be -1 or greater.");
        }

        measurements.add(new Measurement(operationName.trim(), inputSize, steps,
                elapsedNanos, details == null ? "" : details));
    }

    /**
     * Returns all recorded measurements in a readable format.
     */
    public String displayAll() {
        if (measurements.isEmpty()) {
            return "No performance measurements have been recorded.";
        }

        StringBuilder output = new StringBuilder("Performance measurements:");
        for (int index = 0; index < measurements.size(); index++) {
            output.append(System.lineSeparator())
                    .append(index + 1)
                    .append(". ")
                    .append(measurements.get(index));
        }
        return output.toString();
    }

    private static class Measurement {
        private final String operationName;
        private final int inputSize;
        private final long steps;
        private final long elapsedNanos;
        private final String details;

        private Measurement(String operationName, int inputSize, long steps,
                            long elapsedNanos, String details) {
            this.operationName = operationName;
            this.inputSize = inputSize;
            this.steps = steps;
            this.elapsedNanos = elapsedNanos;
            this.details = details;
        }

        @Override
        public String toString() {
            String elapsed = elapsedNanos < 0
                    ? "unavailable"
                    : elapsedNanos + " ns";
            return operationName
                    + " | input size: " + inputSize
                    + " | steps: " + steps
                    + " | elapsed: " + elapsed
                    + " | details: " + details;
        }
    }
}
