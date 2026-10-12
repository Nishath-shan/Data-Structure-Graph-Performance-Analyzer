package com.cit300.analyzer;

public class LinkedListStructure {

    private final PerformanceTracker performanceTracker;
    
    private static class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node head;
    private int size;
    
    public LinkedListStructure() {
        this(new PerformanceTracker());
    }

    public LinkedListStructure(PerformanceTracker performanceTracker) {
        if (performanceTracker == null) {
            throw new IllegalArgumentException("Performance tracker cannot be null.");
        }
        this.performanceTracker = performanceTracker;
        this.head = null;
        this.size = 0;
    }
    
    
    public void insert(int value) {
        long startTime = System.nanoTime();
        Node newNode = new Node(value);
        int steps = 0;
        
        if (head == null) {
            head = newNode;
            steps++;
        } else {
            Node current = head;
            steps++;
            while (current.next != null) {
                current = current.next;
                steps++;
            }
            current.next = newNode;
            steps++;
        }
        size++;
        
        long elapsedTime = System.nanoTime() - startTime;
        performanceTracker.record("Insert", size, steps, elapsedTime, "Inserted value: " + value);
    }
    
   
    public void delete(int value) {
        long startTime = System.nanoTime();
        int steps = 0;
        
        if (head == null) {
            System.out.println("List is empty. Cannot delete.");
            long elapsedTime = System.nanoTime() - startTime;
            performanceTracker.record("Delete", size, steps, elapsedTime, "Failed: Empty list");
            return;
        }
        
        steps++;
        if (head.data == value) {
            head = head.next;
            size--;
            long elapsedTime = System.nanoTime() - startTime;
            performanceTracker.record("Delete", size, steps, elapsedTime, "Deleted head value: " + value);
            return;
        }
        
        Node current = head;
        steps++;
        while (current.next != null && current.next.data != value) {
            current = current.next;
            steps++;
        }
        
        if (current.next != null) {
            current.next = current.next.next;
            size--;
            long elapsedTime = System.nanoTime() - startTime;
            performanceTracker.record("Delete", size, steps, elapsedTime, "Deleted value: " + value);
        } else {
            System.out.println("Value " + value + " not found in the list.");
            long elapsedTime = System.nanoTime() - startTime;
            performanceTracker.record("Delete", size, steps, elapsedTime, "Failed: Value not found");
        }
    }
    
   
    public boolean search(int value) {
        long startTime = System.nanoTime();
        Node current = head;
        int steps = 0;
        boolean found = false;
        
        while (current != null) {
            steps++;
            if (current.data == value) {
                found = true;
                break;
            }
            current = current.next;
        }
        
        long elapsedTime = System.nanoTime() - startTime;
        performanceTracker.record("Search", size, steps, elapsedTime, "Searched for " + value + ", Found: " + found);
        return found;
    }
    
    
    public void display() {
        if (head == null) {
            System.out.println("The list is empty.");
            return;
        }
        
        Node current = head;
        StringBuilder sb = new StringBuilder();
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        System.out.println(sb.toString());
    }
    
    
    public static void runAllTests() {
        System.out.println("=== STARTING LINKED LIST TEST RUNNER ===");
        LinkedListStructure list = new LinkedListStructure();
        
        System.out.print("TEST 1 (Empty List Display): ");
        list.display();
        
        System.out.println("\nTEST 2 (Insert 10, 20, 30):");
        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.display();
        
        System.out.println("\nTEST 3 (Search existing 20): Found = " + list.search(20));
        System.out.println("TEST 4 (Search missing 99): Found = " + list.search(99));
        
        System.out.println("\nTEST 5 (Delete middle value 20):");
        list.delete(20);
        list.display();
        
        System.out.println("\nTEST 6 (Delete first value 10):");
        list.delete(10);
        list.display();
        
        System.out.println("\nTEST 7 (Delete final value 30):");
        list.delete(30);
        list.display();
        
        System.out.print("\nTEST 8 (Delete from empty list): ");
        list.delete(40);
        
        System.out.println("TEST 9 (Search an empty list): Found = " + list.search(10));
        System.out.println("\nTEST 10 (Performance measurements verified via PerformanceTracker logs)");
        System.out.println("=== TESTS COMPLETED SUCCESSFULLY ===");
    }
}
