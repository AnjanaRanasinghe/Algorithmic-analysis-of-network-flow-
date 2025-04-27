/*
 * Student ID: 20231160
 * UOW ID: w2051972
 * Name: A.A.S.Ranasinghe
 * Represents a flow network using an adjacency list.
 */

import java.util.*;

public class FlowNetwork {
    private int numNodes; // Number of nodes (0 to numNodes-1)
    private List<Map<Integer, Edge>> adjList; // Adjacency list: node -> {dest -> Edge}

    // Edge class to store capacity and flow
    static class Edge {
        int capacity;
        int flow;
        int dest; // Destination node

        Edge(int dest, int capacity) {
            this.dest = dest;
            this.capacity = capacity;
            this.flow = 0;
        }
    }

    // Constructor
    public FlowNetwork(int numNodes) {
        this.numNodes = numNodes;
        this.adjList = new ArrayList<>(numNodes);
        for (int i = 0; i < numNodes; i++) {
            adjList.add(new HashMap<>());
        }
    }

    // Add a directed edge from src to dest with capacity
    public void addEdge(int src, int dest, int capacity) {
        adjList.get(src).put(dest, new Edge(dest, capacity));
        // Add reverse edge with 0 capacity for residual graph
        if (!adjList.get(dest).containsKey(src)) {
            adjList.get(dest).put(src, new Edge(src, 0));
        }
    }

    // Get edge from src to dest
    public Edge getEdge(int src, int dest) {
        return adjList.get(src).get(dest);
    }

    // Get all edges from a node
    public Map<Integer, Edge> getEdges(int node) {
        return adjList.get(node);
    }

    // Get number of nodes
    public int getNumNodes() {
        return numNodes;
    }
}