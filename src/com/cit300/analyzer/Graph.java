package com.cit300.analyzer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Graph {
    private final Map<String, Set<String>> adjacencyList = new LinkedHashMap<>();
    private final PerformanceTracker performanceTracker;

    public Graph(PerformanceTracker performanceTracker) {
        if (performanceTracker == null) {
            throw new IllegalArgumentException("Performance tracker cannot be null.");
        }
        this.performanceTracker = performanceTracker;
    }

    /**
     * Adds a vertex unless its name is null, blank, or already present.
     */
    public boolean addVertex(String vertex) {
        String validVertex = normalizeVertex(vertex);
        if (validVertex == null || adjacencyList.containsKey(validVertex)) {
            return false;
        }

        adjacencyList.put(validVertex, new LinkedHashSet<String>());
        return true;
    }

    /**
     * Adds an undirected edge between two existing, distinct vertices.
     */
    public boolean addEdge(String firstVertex, String secondVertex) {
        String first = normalizeVertex(firstVertex);
        String second = normalizeVertex(secondVertex);

        if (first == null || second == null
                || first.equals(second)
                || !adjacencyList.containsKey(first)
                || !adjacencyList.containsKey(second)
                || adjacencyList.get(first).contains(second)) {
            return false;
        }

        adjacencyList.get(first).add(second);
        adjacencyList.get(second).add(first);
        return true;
    }

    /**
     * Returns every vertex and its adjacent vertices in insertion order.
     */
    public String display() {
        if (adjacencyList.isEmpty()) {
            return "Graph is empty.";
        }

        StringBuilder output = new StringBuilder();
        for (Map.Entry<String, Set<String>> entry : adjacencyList.entrySet()) {
            if (output.length() > 0) {
                output.append(System.lineSeparator());
            }
            output.append(entry.getKey()).append(" -> ");
            if (entry.getValue().isEmpty()) {
                output.append("(no adjacent vertices)");
            } else {
                output.append(entry.getValue());
            }
        }
        return output.toString();
    }

    /**
     * Performs breadth-first traversal, examining adjacent vertices in insertion order.
     * A missing or invalid start vertex returns an empty list and records no measurement.
     */
    public List<String> breadthFirstTraversal(String startVertex) {
        String start = normalizeVertex(startVertex);
        if (start == null || !adjacencyList.containsKey(start)) {
            return Collections.emptyList();
        }

        long startTime = System.nanoTime();
        List<String> traversalOrder = new ArrayList<>();
        Set<String> visited = new LinkedHashSet<>();
        Queue<String> pending = new ArrayDeque<>();
        long adjacencyEntriesExamined = 0;

        visited.add(start);
        pending.add(start);
        while (!pending.isEmpty()) {
            String current = pending.remove();
            traversalOrder.add(current);

            for (String neighbor : adjacencyList.get(current)) {
                adjacencyEntriesExamined++;
                if (visited.add(neighbor)) {
                    pending.add(neighbor);
                }
            }
        }

        long elapsedNanos = System.nanoTime() - startTime;
        recordTraversal("BFS", traversalOrder, adjacencyEntriesExamined, elapsedNanos);
        return traversalOrder;
    }

    /**
     * Performs depth-first traversal, examining adjacent vertices in insertion order.
     * A missing or invalid start vertex returns an empty list and records no measurement.
     */
    public List<String> depthFirstTraversal(String startVertex) {
        String start = normalizeVertex(startVertex);
        if (start == null || !adjacencyList.containsKey(start)) {
            return Collections.emptyList();
        }

        long startTime = System.nanoTime();
        List<String> traversalOrder = new ArrayList<>();
        Set<String> visited = new LinkedHashSet<>();
        long[] adjacencyEntriesExamined = {0};

        depthFirstVisit(start, visited, traversalOrder, adjacencyEntriesExamined);

        long elapsedNanos = System.nanoTime() - startTime;
        recordTraversal("DFS", traversalOrder, adjacencyEntriesExamined[0], elapsedNanos);
        return traversalOrder;
    }

    private void depthFirstVisit(String vertex, Set<String> visited,
                                 List<String> traversalOrder,
                                 long[] adjacencyEntriesExamined) {
        visited.add(vertex);
        traversalOrder.add(vertex);

        for (String neighbor : adjacencyList.get(vertex)) {
            adjacencyEntriesExamined[0]++;
            if (!visited.contains(neighbor)) {
                depthFirstVisit(neighbor, visited, traversalOrder,
                        adjacencyEntriesExamined);
            }
        }
    }

    private void recordTraversal(String operationName, List<String> traversalOrder,
                                 long adjacencyEntriesExamined, long elapsedNanos) {
        long verticesVisited = traversalOrder.size();
        long steps = verticesVisited + adjacencyEntriesExamined;
        String details = "vertices visited: " + verticesVisited
                + ", adjacency entries examined: " + adjacencyEntriesExamined
                + ", order: " + traversalOrder;

        performanceTracker.record(operationName, adjacencyList.size(), steps,
                elapsedNanos, details);
    }

    private String normalizeVertex(String vertex) {
        if (vertex == null) {
            return null;
        }
        String trimmedVertex = vertex.trim();
        return trimmedVertex.isEmpty() ? null : trimmedVertex;
    }
}
