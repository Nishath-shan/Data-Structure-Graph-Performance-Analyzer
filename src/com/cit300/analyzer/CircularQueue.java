package com.cit300.analyzer;

/**
 * A simple integer queue implemented using a circular array.
 * It follows FIFO order: the first value added is the first value removed.
 */
public class CircularQueue {
    private static final int DEFAULT_CAPACITY = 10;

    private final int[] queue;
    private int front;
    private int rear;
    private int size;
    private final PerformanceTracker performanceTracker;

    public CircularQueue() {
        this(DEFAULT_CAPACITY);
    }

    public CircularQueue(int capacity) {
        this(capacity, null);
    }

    public CircularQueue(PerformanceTracker performanceTracker) {
        this(DEFAULT_CAPACITY, performanceTracker);
    }

    public CircularQueue(int capacity, PerformanceTracker performanceTracker) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Queue capacity must be greater than zero.");
        }
        this.queue = new int[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
        this.performanceTracker = performanceTracker;
    }

    /**
     * Adds a value to the rear of the queue.
     * Returns true if the value was added, false if the queue is full.
     */
    public boolean enqueue(int value) {
        long startTime = System.nanoTime();

        if (isFull()) {
            recordMeasurement("ENQUEUE", size, 0, System.nanoTime() - startTime,
                    "Queue is full. Value was not added.");
            return false;
        }

        queue[rear] = value;
        rear = (rear + 1) % queue.length;
        size++;

        recordMeasurement("ENQUEUE", size, size, System.nanoTime() - startTime,
                "Added value: " + value);
        return true;
    }

    /**
     * Removes and returns the front value.
     * If the queue is empty, it safely returns -1.
     */
    public int dequeue() {
        long startTime = System.nanoTime();

        if (isEmpty()) {
            recordMeasurement("DEQUEUE", size, 0, System.nanoTime() - startTime,
                    "Queue is empty. No value removed.");
            return -1;
        }

        int value = queue[front];
        front = (front + 1) % queue.length;
        size--;

        recordMeasurement("DEQUEUE", size, size, System.nanoTime() - startTime,
                "Removed value: " + value);
        return value;
    }

    /**
     * Returns the front value without removing it.
     * If the queue is empty, it safely returns -1.
     */
    public int peek() {
        long startTime = System.nanoTime();

        if (isEmpty()) {
            recordMeasurement("PEEK", size, 0, System.nanoTime() - startTime,
                    "Queue is empty. No front value.");
            return -1;
        }

        int value = queue[front];
        recordMeasurement("PEEK", size, size, System.nanoTime() - startTime,
                "Front value: " + value);
        return value;
    }

    /**
     * Returns a readable description of the queue from front to rear.
     */
    public String display() {
        long startTime = System.nanoTime();
        String result;

        if (isEmpty()) {
            result = "Queue is empty.";
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append("Queue (front to rear): ");
            for (int count = 0; count < size; count++) {
                int index = (front + count) % queue.length;
                if (count > 0) {
                    builder.append(", ");
                }
                builder.append(queue[index]);
            }
            result = builder.toString();
        }

        recordMeasurement("DISPLAY", size, size, System.nanoTime() - startTime, result);
        System.out.println(result);
        return result;
    }

    public int front() {
        return peek();
    }

    public int getFront() {
        return peek();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == queue.length;
    }

    public int capacity() {
        return queue.length;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Queue is empty.";
        }

        StringBuilder builder = new StringBuilder();
        for (int count = 0; count < size; count++) {
            int index = (front + count) % queue.length;
            if (count > 0) {
                builder.append(", ");
            }
            builder.append(queue[index]);
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
