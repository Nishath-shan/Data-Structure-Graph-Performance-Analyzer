# 📊 Data Structure and Graph Performance Analyzer

## 📘 Project Overview

This Java console application lets students try common data structures and algorithms. It includes array, stack, queue, linked-list, and graph operations, along with search algorithms and performance measurements.

| Assignment detail | Information |
|---|---|
| Module | CIT300 – Data Structures and Algorithms |
| Assignment | Graded Practical Assignment 2 |

## 👥 Team Members

| Name | Student ID | Responsibility |
|---|---|---|
| M.R.M. Nishath | 23DA2-1016 | Team Leader; graph operations, BFS, DFS, and system integration |
| Mohammed Azam | 23DA2-1044 | Array operations and searching algorithms |
| M.I. Rimas Ahamad | 23DA2-0828 | Stack and queue operations |
| H.K. Chathurika Harshani | 23DA2-0886 | Linked-list operations |

## 👨‍💻 Individual Contributions

Each team member contributed to their assigned component of the project.

- **M.R.M. Nishath (Team Leader):** Responsible for graph operations, BFS and DFS traversal, and integrating the components into the main application.
- **Mohammed Azam:** Responsible for array operations and searching algorithms, including linear search and binary search.
- **M.I. Rimas Ahamad:** Responsible for stack and circular queue operations.
- **H.K. Chathurika Harshani:** Responsible for linked-list operations, including insertion, deletion, searching, and displaying values.

## 🛠️ Technologies Used

| Technology | Use |
|---|---|
| Java | Application programming language |
| JDK 21 | Compiling and running the project |
| Git and GitHub | Version control and team collaboration |
| Windows PowerShell | Commands for compiling and running on Windows |

The project uses standard Java libraries and needs no additional dependencies.

## ✨ Main Features

| Feature | What you can do |
|---|---|
| Array operations | Insert, delete, search, and display integer values |
| Stack operations | Push, pop, peek, and display values in LIFO order |
| Queue operations | Enqueue, dequeue, peek, and display values in FIFO order |
| Linked-list operations | Insert, delete, search, and display integer values |
| Searching | Run linear search or binary search on integer values |
| Graph operations | Add vertices and edges, display the graph, and run BFS or DFS |
| Performance comparison | Compare linear and binary search on the same sorted values and target |
| Display all results | View performance measurements recorded during the current run |

## 🧠 Data Structures and Algorithms

| Structure or algorithm | Use | Typical time complexity |
|---|---|---|
| Dynamic array | Stores the values used by array operations | Search and indexed insert/delete: O(n); append: amortized O(1) |
| Array-based stack | Stores values in last-in, first-out (LIFO) order | Push, pop, and peek: O(1) |
| Circular-array queue | Stores values in first-in, first-out (FIFO) order | Enqueue, dequeue, and peek: O(1) |
| Singly linked list | Stores integer nodes in order | Insert at end, delete by value, and search: O(n) |
| Adjacency-list graph | Stores vertices and their connections | BFS and DFS: O(V + E) |
| Linear search | Checks values in order | O(n) |
| Binary search | Repeatedly halves the search range | O(log n); input must be sorted in ascending order |

Here, **V** is the number of vertices and **E** is the number of edges. A graph traversal visits vertices reachable from its starting vertex.

## 🗂️ Project Structure

```text
Data-Structure-Graph-Performance-Analyzer/
+-- .gitignore
+-- README.md
+-- src/
    +-- com/
        +-- cit300/
            +-- analyzer/
                +-- ArrayOperations.java
                +-- CircularQueue.java
                +-- Graph.java
                +-- IntStack.java
                +-- LinkedListStructure.java
                +-- Main.java
                +-- PerformanceTracker.java
                +-- SearchAlgorithms.java
```

All Java files use the `com.cit300.analyzer` package.

## ✅ Requirements

- Windows with PowerShell, for the commands below.
- JDK 21.
- No third-party libraries or additional dependencies.

## ▶️ How to Compile and Run

Run these commands from the project folder in PowerShell:

```powershell
New-Item -ItemType Directory -Force out
javac -d out src\com\cit300\analyzer\*.java
java -cp out com.cit300.analyzer.Main
```

The first command creates an output folder, the second compiles the Java files, and the third starts the application.

## 📈 Performance Measurement

The shared `PerformanceTracker` displays measurements recorded by supported operations. A measurement can include the operation name, input size, counted steps, elapsed time in nanoseconds, and a short result description.

Step counts depend on the operation. For example, searches count comparisons, while graph traversals count visited vertices and examined connections. Elapsed times can vary between runs. Search comparison times cover the searches, not the time used to sort the values. Measurements are kept in memory and are cleared when the application exits.

## 🤝 Team Collaboration

The GitHub history shows feature commits, work on branches, and two merged pull requests. The recorded work covers the project components and application integration. The repository history does not verify that every commit author account belongs to a particular named team member, so responsibilities above follow the team information provided for this assignment.

## 🎓 Conclusion

This project brings data structures, searching, and graph traversal together in one console application. Students can try the operations and see how their measured work varies with the input.
