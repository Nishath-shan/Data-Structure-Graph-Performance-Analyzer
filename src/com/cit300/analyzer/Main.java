package com.cit300.analyzer;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final PerformanceTracker performanceTracker = new PerformanceTracker();
    private final ArrayOperations arrayOperations = new ArrayOperations(performanceTracker);
    private final IntStack stack = new IntStack(performanceTracker);
    private final CircularQueue queue = new CircularQueue(performanceTracker);
    private final LinkedListStructure linkedList = new LinkedListStructure(performanceTracker);
    private final SearchAlgorithms searchAlgorithms = new SearchAlgorithms(performanceTracker);
    private final Graph graph = new Graph(performanceTracker);
    private boolean inputClosed;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        System.out.println("DATA STRUCTURE & GRAPH ANALYZER");

        while (!inputClosed) {
            System.out.println();
            System.out.println("1. Array Operations");
            System.out.println("2. Stack Operations");
            System.out.println("3. Queue Operations");
            System.out.println("4. Linked List Operations");
            System.out.println("5. Searching Operations");
            System.out.println("6. Graph Operations");
            System.out.println("7. Performance Comparison");
            System.out.println("8. Display All Results");
            System.out.println("9. Exit");

            Integer choice = readInt("Choose an option: ");
            if (choice == null) {
                break;
            }

            switch (choice) {
                case 1:
                    arrayMenu();
                    break;
                case 2:
                    stackMenu();
                    break;
                case 3:
                    queueMenu();
                    break;
                case 4:
                    linkedListMenu();
                    break;
                case 5:
                    searchingMenu();
                    break;
                case 6:
                    graphMenu();
                    break;
                case 7:
                    compareSearches();
                    break;
                case 8:
                    System.out.println(performanceTracker.displayAll());
                    break;
                case 9:
                    System.out.println("Goodbye.");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 9.");
            }
        }
    }

    private void arrayMenu() {
        while (!inputClosed) {
            System.out.println("\nARRAY OPERATIONS");
            System.out.println("1. Insert value");
            System.out.println("2. Delete value");
            System.out.println("3. Search value");
            System.out.println("4. Display array");
            System.out.println("5. Return to Main Menu");
            Integer choice = readInt("Choose an option: ");
            if (choice == null) {
                return;
            }

            switch (choice) {
                case 1: {
                    Integer index = readInt("Insert at index (0 to current size): ");
                    if (index == null) {
                        return;
                    }
                    Integer value = readInt("Enter the value: ");
                    if (value == null) {
                        return;
                    }
                    try {
                        arrayOperations.insert(index, value);
                        System.out.println("Value inserted.");
                    } catch (IndexOutOfBoundsException exception) {
                        System.out.println("Invalid index. " + exception.getMessage());
                    }
                    break;
                }
                case 2: {
                    Integer value = readInt("Enter the value to delete: ");
                    if (value == null) {
                        return;
                    }
                    if (arrayOperations.delete(value)) {
                        System.out.println("Value deleted.");
                    } else {
                        System.out.println("Value not found; the array may be empty.");
                    }
                    break;
                }
                case 3: {
                    Integer value = readInt("Enter the value to search for: ");
                    if (value == null) {
                        return;
                    }
                    int index = arrayOperations.search(value);
                    System.out.println(index >= 0
                            ? "Value found at index " + index + "."
                            : "Value not found; the array may be empty.");
                    break;
                }
                case 4:
                    System.out.println(arrayOperations.display());
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
    }

    private void stackMenu() {
        while (!inputClosed) {
            System.out.println("\nSTACK OPERATIONS");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Display stack");
            System.out.println("5. Return to Main Menu");
            Integer choice = readInt("Choose an option: ");
            if (choice == null) {
                return;
            }

            switch (choice) {
                case 1: {
                    Integer value = readInt("Enter the value to push: ");
                    if (value == null) {
                        return;
                    }
                    System.out.println(stack.push(value)
                            ? "Value pushed."
                            : "Stack is full; value was not added.");
                    break;
                }
                case 2:
                    if (stack.isEmpty()) {
                        System.out.println("Stack is empty; nothing to pop.");
                    } else {
                        System.out.println("Popped value: " + stack.pop());
                    }
                    break;
                case 3:
                    if (stack.isEmpty()) {
                        System.out.println("Stack is empty; there is no top value.");
                    } else {
                        System.out.println("Top value: " + stack.peek());
                    }
                    break;
                case 4:
                    System.out.println(stack.display());
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
    }

    private void queueMenu() {
        while (!inputClosed) {
            System.out.println("\nQUEUE OPERATIONS");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek at front");
            System.out.println("4. Display queue");
            System.out.println("5. Return to Main Menu");
            Integer choice = readInt("Choose an option: ");
            if (choice == null) {
                return;
            }

            switch (choice) {
                case 1: {
                    Integer value = readInt("Enter the value to enqueue: ");
                    if (value == null) {
                        return;
                    }
                    System.out.println(queue.enqueue(value)
                            ? "Value enqueued."
                            : "Queue is full; value was not added.");
                    break;
                }
                case 2:
                    if (queue.isEmpty()) {
                        System.out.println("Queue is empty; nothing to dequeue.");
                    } else {
                        System.out.println("Dequeued value: " + queue.dequeue());
                    }
                    break;
                case 3:
                    if (queue.isEmpty()) {
                        System.out.println("Queue is empty; there is no front value.");
                    } else {
                        System.out.println("Front value: " + queue.peek());
                    }
                    break;
                case 4:
                    queue.display();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
    }

    private void linkedListMenu() {
        while (!inputClosed) {
            System.out.println("\nLINKED LIST OPERATIONS");
            System.out.println("1. Insert value");
            System.out.println("2. Delete value");
            System.out.println("3. Search value");
            System.out.println("4. Display list");
            System.out.println("5. Return to Main Menu");
            Integer choice = readInt("Choose an option: ");
            if (choice == null) {
                return;
            }

            switch (choice) {
                case 1: {
                    Integer value = readInt("Enter the value to insert: ");
                    if (value == null) {
                        return;
                    }
                    linkedList.insert(value);
                    System.out.println("Value inserted.");
                    break;
                }
                case 2: {
                    Integer value = readInt("Enter the value to delete: ");
                    if (value == null) {
                        return;
                    }
                    linkedList.delete(value);
                    break;
                }
                case 3: {
                    Integer value = readInt("Enter the value to search for: ");
                    if (value == null) {
                        return;
                    }
                    System.out.println(linkedList.search(value)
                            ? "Value found."
                            : "Value not found; the list may be empty.");
                    break;
                }
                case 4:
                    linkedList.display();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
    }

    private void searchingMenu() {
        while (!inputClosed) {
            System.out.println("\nSEARCHING OPERATIONS");
            System.out.println("1. Linear Search");
            System.out.println("2. Binary Search");
            System.out.println("3. Return to Main Menu");
            Integer choice = readInt("Choose an option: ");
            if (choice == null) {
                return;
            }
            if (choice == 3) {
                return;
            }
            if (choice != 1 && choice != 2) {
                System.out.println("Invalid choice. Please enter a number from 1 to 3.");
                continue;
            }

            int[] values = readValues();
            if (values == null) {
                return;
            }
            if (choice == 2) {
                Arrays.sort(values);
                System.out.println("Values sorted for binary search: " + Arrays.toString(values));
            }
            Integer target = readInt("Enter the target value: ");
            if (target == null) {
                return;
            }

            int index = choice == 1
                    ? searchAlgorithms.linearSearch(values, target)
                    : searchAlgorithms.binarySearch(values, target);
            printSearchResult(index);
        }
    }

    private void graphMenu() {
        while (!inputClosed) {
            System.out.println("\nGRAPH OPERATIONS");
            System.out.println("1. Add Vertex");
            System.out.println("2. Add Edge");
            System.out.println("3. Display Graph");
            System.out.println("4. BFS Traversal");
            System.out.println("5. DFS Traversal");
            System.out.println("6. Return to Main Menu");
            Integer choice = readInt("Choose an option: ");
            if (choice == null) {
                return;
            }

            switch (choice) {
                case 1: {
                    String vertex = readText("Enter the vertex name: ");
                    if (vertex == null) {
                        return;
                    }
                    System.out.println(graph.addVertex(vertex)
                            ? "Vertex added."
                            : "Vertex not added. Its name may be blank or already in the graph.");
                    break;
                }
                case 2: {
                    String first = readText("Enter the first existing vertex: ");
                    if (first == null) {
                        return;
                    }
                    String second = readText("Enter the second existing vertex: ");
                    if (second == null) {
                        return;
                    }
                    System.out.println(graph.addEdge(first, second)
                            ? "Undirected edge added."
                            : "Edge not added. Check that both vertices exist, are distinct, and are not already connected.");
                    break;
                }
                case 3:
                    System.out.println(graph.display());
                    break;
                case 4:
                    traverseGraph(true);
                    break;
                case 5:
                    traverseGraph(false);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 6.");
            }
        }
    }

    private void traverseGraph(boolean breadthFirst) {
        String start = readText("Enter the starting vertex: ");
        if (start == null) {
            return;
        }
        List<String> traversal = breadthFirst
                ? graph.breadthFirstTraversal(start)
                : graph.depthFirstTraversal(start);
        if (traversal.isEmpty()) {
            System.out.println("Traversal could not start. The graph may be empty or the vertex may not exist.");
        } else {
            System.out.println((breadthFirst ? "BFS" : "DFS") + " order: " + traversal);
        }
    }

    private void compareSearches() {
        System.out.println("\nPERFORMANCE COMPARISON");
        int[] values = readValues();
        if (values == null) {
            return;
        }

        Arrays.sort(values);
        System.out.println("Both searches will use this same sorted data: "
                + Arrays.toString(values));
        Integer target = readInt("Enter the target value: ");
        if (target == null) {
            return;
        }

        int linearIndex = searchAlgorithms.linearSearch(values, target);
        int binaryIndex = searchAlgorithms.binarySearch(values, target);
        System.out.println("Linear search: " + (linearIndex >= 0
                ? "found at index " + linearIndex
                : "not found"));
        System.out.println("Binary search: " + (binaryIndex >= 0
                ? "found at index " + binaryIndex
                : "not found"));
        System.out.println("Linear search has O(n) time complexity; binary search has O(log n) "
                + "time complexity on sorted data.");
        System.out.println("The tracker records the actual comparisons and elapsed time for each search.");
    }

    private int[] readValues() {
        Integer count = readInt("How many integer values? ");
        if (count == null) {
            return null;
        }
        while (count < 0) {
            System.out.println("Please enter zero or a positive number.");
            count = readInt("How many integer values? ");
            if (count == null) {
                return null;
            }
        }

        int[] values = new int[count];
        for (int index = 0; index < count; index++) {
            Integer value = readInt("Enter value " + (index + 1) + ": ");
            if (value == null) {
                return null;
            }
            values[index] = value;
        }
        return values;
    }

    private void printSearchResult(int index) {
        System.out.println(index >= 0
                ? "Target found at index " + index + "."
                : "Target not found.");
    }

    private Integer readInt(String prompt) {
        while (!inputClosed) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                inputClosed = true;
                return null;
            }
            String text = scanner.nextLine().trim();
            try {
                return Integer.valueOf(text);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid whole number.");
            }
        }
        return null;
    }

    private String readText(String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextLine()) {
            inputClosed = true;
            return null;
        }
        return scanner.nextLine();
    }
}
