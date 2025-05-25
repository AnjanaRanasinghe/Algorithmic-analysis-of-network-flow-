/*
 * Student ID: 20231160
 * UOW ID: w2051972
 * Name: A.A.S.Ranasinghe
 * Implements the Ford-Fulkerson algorithm with BFS to compute maximum flow.
 */


import java.util.*;

public class FordFulkerson {
    private FlowNetwork network;    // The flow network
    private int source;             // Source node (assumed to be node 0)
    private int sink;               // Sink node (assumed to be the last node)
    private List<String> steps;     // List to store incremental steps for tracing

    // Constructor
    public FordFulkerson(FlowNetwork network) {
        this.network = network;
        this.source = 0;
        this.sink = network.getNumNodes() - 1;
        this.steps = new ArrayList<>();
    }

    /**
     * Performs Breadth-First Search (BFS) to find an augmenting path
     * from the source to the sink.
     * @return parent array representing the path. If sink is unreachable, parent[sink] == -1.
     */
    private int[] bfs() {
        int[] parent = new int[network.getNumNodes()];
        Arrays.fill(parent, -1);          // Initialize all nodes as unvisited
        Queue<Integer> queue = new LinkedList<>();   // Creates a queue for BFS, using LinkedList
        queue.add(source);
        parent[source] = source;    // Mark source as visited by pointing to itself

        while (!queue.isEmpty()) {
            int u = queue.poll();    // removes the head (first element)
            for (Map.Entry<Integer, FlowNetwork.Edge> entry : network.getEdges(u).entrySet()) {   // Iterates over all edges from node u
                int v = entry.getKey();     // Gets the destination node v of the edge
                FlowNetwork.Edge edge = entry.getValue();       // Gets the Edge object, which contains capacity and flow

                // Check if edge has residual capacity and node is unvisited
                // parent[v] == -1: Ensures node v hasn’t been visited
                // edge.capacity - edge.flow > 0: Ensures the edge has residual capacity, meaning more flow can be pushed from u to v
                if (parent[v] == -1 && edge.capacity - edge.flow > 0) {
                    parent[v] = u;   // Sets u as the parent of v, recording the path
                    queue.add(v);
                }
            }
        }
        return parent;
    }

    /**
     * Computes the maximum flow from the source to the sink.
     * @return the value of the maximum flow.
     */
    public int computeMaxFlow() {
        int maxFlow = 0;
        steps.clear();       // Clear previous steps before starting a new computation

        while (true) {
            // Find augmenting path using called bfs function
            int[] parent = bfs();
            if (parent[sink] == -1) {
                break; // No augmenting path, maximum flow reached
            }

            // Find the minimum residual capacity (bottleneck) along the path
            int bottleneck = Integer.MAX_VALUE;
            List<Integer> path = new ArrayList<>();
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                FlowNetwork.Edge edge = network.getEdge(u, v);
                bottleneck = Math.min(bottleneck, edge.capacity - edge.flow);
                path.add(v);
            }
            path.add(source);
            Collections.reverse(path);      // Reverse to get path from source to sink

            // Update the flow along the path
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                FlowNetwork.Edge forward = network.getEdge(u, v);
                FlowNetwork.Edge reverse = network.getEdge(v, u);
                forward.flow += bottleneck;   // Increase flow along the forward edge
                reverse.flow -= bottleneck;   // Decrease  flow along the forward edge
            }

            maxFlow += bottleneck;
            steps.add("Path: " + path + ", Flow added: " + bottleneck);  // Record the step
        }

        steps.add("Maximum flow: " + maxFlow);   // Record final result
        return maxFlow;
    }

    // Returns a list of all incremental steps recorded during the computation
    public List<String> getSteps() {
        return steps;
    }
}