# Maximum Flow Solver

This repository contains my coursework project for the Algorithms: Theory, Design and Implementation module at the Informatics Institute of Technology, part of the BSc (Hons) Computer Science program. The project implements a maximum flow solver using the Ford-Fulkerson algorithm with Breadth-First Search (BFS) to compute the maximum flow in a directed graph.

## Project Overview
The goal of this project was to design and implement a maximum flow solver for directed graphs, tested on 39 benchmark files. The implementation includes a user-friendly menu to select benchmark files, compute the maximum flow, and display augmenting paths and flow increments. The solver uses efficient data structures and algorithms to ensure performance and clarity in output.

## Key Features
Algorithm: Ford-Fulkerson with BFS for finding the shortest augmenting paths.
Data Structure: Adjacency list using ArrayList and HashMap for O(1) edge lookups and updates.
Input Handling: Supports 39 benchmark files (e.g., bridge_1.txt) with a menu-driven interface.
Output: Displays augmenting paths and flow increments, as demonstrated in the report (e.g., max flow of 5 for bridge_1.txt).
Performance: Time complexity of O(V·E²) and space complexity of O(V + E), optimized for sparse graphs.

Repository Structure

src/: Source code for the maximum flow solver implemented in Java.
benchmarks/: Directory containing the 39 benchmark files (e.g., bridge_1.txt).
docs/: Project report (w2051972_A.A.S.Ranasinghe_Report.pdf) detailing the implementation, algorithm, and performance analysis.
demo/: Demo video showcasing the solver in action on sample benchmarks.

Getting Started
Prerequisites

Java Development Kit (JDK) 8 or higher
Any IDE or text editor (e.g., IntelliJ IDEA, VS Code)

Installation

Clone the repository:git clone https://github.com/yourusername/max-flow-solver.git


Navigate to the project directory:cd max-flow-solver


Compile and run the Java program:javac src/*.java
java src/Main



Usage

Run the program to access the menu.
Select a benchmark file from the provided list (e.g., bridge_1.txt).
The program computes the maximum flow and outputs the augmenting paths and flow increments.
Refer to the demo video in the demo/ folder for a visual walkthrough.

Example
For the benchmark bridge_1.txt (6 nodes, source: 0, sink: 5):

Maximum flow: 5 units
Augmenting paths:
Path 1: [0, 1, 5] → Flow: 1 unit
Path 2: [0, 1, 4, 5] → Flow: 1 unit
Path 3: [0, 1, 3, 4, 5] → Flow: 1 unit
Path 4: [0, 1, 2, 3, 4, 5] → Flow: 1 unit



Performance Analysis

Time Complexity: O(V·E²), where V is the number of nodes and E is the number of edges.
Space Complexity: O(V + E) for the adjacency list and BFS queue.
Optimized for sparse graphs with O(1) edge lookups using HashMap.

Demo Video
A demo video is included in the demo/ folder, showcasing the solver's functionality on sample benchmarks. It demonstrates file selection, flow computation, and output of augmenting paths.
Report
The full project report (w2051972_A.A.S.Ranasinghe_Report.pdf) is available in the docs/ folder. It includes:

Introduction to the project
Explanation of data structures and algorithm choices
Example run on bridge_1.txt
Detailed performance analysis

Acknowledgments

Informatics Institute of Technology for the coursework structure.
My instructors and peers for valuable feedback.

Feel free to explore the code, run the solver, or watch the demo video! For any questions or suggestions, please open an issue or contact me via LinkedIn: [Insert your LinkedIn profile link].
