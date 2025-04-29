/*
 * Student ID: 20231160
 * UOW ID: w2051972
 * Name: A.A.S.Ranasinghe
 * Implements the Ford-Fulkerson algorithm with BFS to compute maximum flow.
 */


import java.util.*;

public class FordFulkerson {
    private FlowNetwork network;
    private int source;
    private int sink;
    private List<String> steps; // Stores incremental steps for output

    public FordFulkerson(FlowNetwork network) {
        this.network = network;
        this.source = 0;
        this.sink = network.getNumNodes() - 1;
        this.steps = new ArrayList<>();
    }

    // BFS to find an augmenting path (shortest path in terms of number of edges)
    private int[] bfs() {
        int[] parent = new int[network.getNumNodes()];
        Arrays.fill(parent, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        parent[source] = source;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Map.Entry<Integer, FlowNetwork.Edge> entry : network.getEdges(u).entrySet()) {
                int v = entry.getKey();
                FlowNetwork.Edge edge = entry.getValue();
                // Check if edge has residual capacity and node is unvisited
                if (parent[v] == -1 && edge.capacity - edge.flow > 0) {
                    parent[v] = u;
                    queue.add(v);
                }
            }
        }
        return parent;
    }

    // Compute maximum flow
    public int computeMaxFlow() {
        int maxFlow = 0;
        steps.clear();

        while (true) {
            // Find augmenting path using BFS
            int[] parent = bfs();
            if (parent[sink] == -1) {
                break; // No augmenting path
            }

            // Find bottleneck capacity
            int bottleneck = Integer.MAX_VALUE;
            List<Integer> path = new ArrayList<>();
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                FlowNetwork.Edge edge = network.getEdge(u, v);
                bottleneck = Math.min(bottleneck, edge.capacity - edge.flow);
                path.add(v);
            }
            path.add(source);
            Collections.reverse(path);

            // Update flows
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                FlowNetwork.Edge forward = network.getEdge(u, v);
                FlowNetwork.Edge reverse = network.getEdge(v, u);
                forward.flow += bottleneck;
                reverse.flow -= bottleneck;
            }

            maxFlow += bottleneck;
            steps.add("Augmenting path: " + path + ", Flow added: " + bottleneck);
        }

        steps.add("Maximum flow: " + maxFlow);
        return maxFlow;
    }

    // Get incremental steps
    public List<String> getSteps() {
        return steps;
    }
}