# Maximum Flow Solver

This repository contains my coursework project for the Algorithms: Theory, Design and Implementation module at the Informatics Institute of Technology, part of the BSc (Hons) Computer Science program. The project implements a maximum flow solver using the Ford-Fulkerson algorithm with Breadth-First Search (BFS) to compute the maximum flow in a directed graph.

---

## Project Overview
The goal of this project was to design and implement a maximum flow solver for directed graphs, tested on 39 benchmark files. The implementation includes a user-friendly menu to select benchmark files, compute the maximum flow, and display augmenting paths and flow increments. The solver uses efficient data structures and algorithms to ensure performance and clarity in output.

---

## Key Features
**Algorithm:** Ford-Fulkerson with BFS for finding the shortest augmenting paths.

**Data Structure:** Adjacency list using ArrayList and HashMap for O(1) edge lookups and updates.

**Input Handling:** Supports 39 benchmark files (e.g., bridge_1.txt) with a menu-driven interface.

**Output:** Displays augmenting paths and flow increments, as demonstrated in the report (e.g., max flow of 5 for bridge_1.txt).

**Performance:** Time complexity of O(V·E²) and space complexity of O(V + E), optimized for sparse graphs.

---

## Prerequisites

Java Development Kit (JDK) 8 or higher
Any IDE or text editor (e.g., IntelliJ IDEA, VS Code)

---

## Installation
Clone the repository:git clone https://github.com/yourusername/max-flow-solver.git

Navigate to the project directory:cd max-flow-solver

Compile and run the Java program:javac src/*.java

java src/Main

---

## Usage
Run the program to access the menu.

Select a benchmark file from the provided list (e.g., bridge_1.txt).

The program computes the maximum flow and outputs the augmenting paths and flow increments.

Refer to the demo video in the demo/ folder for a visual walkthrough.

---

## Example

<img width="584" height="223" alt="image" src="https://github.com/user-attachments/assets/bdb92bfe-1a34-426a-817f-56b17a30d708" />

---

## Performance Analysis

Time Complexity: O(V·E²), where V is the number of nodes and E is the number of edges.
Space Complexity: O(V + E) for the adjacency list and BFS queue.
Optimized for sparse graphs with O(1) edge lookups using HashMap.

---

Acknowledgments

Informatics Institute of Technology for the coursework structure.
My instructors and peers for valuable feedback.

Feel free to explore the code, run the solver, or watch the demo video! For any questions or suggestions, please open an issue or contact me via LinkedIn: [Insert your LinkedIn profile link].
