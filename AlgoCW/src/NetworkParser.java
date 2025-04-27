/*
 * Student ID: 20231160
 * UOW ID: w2051972
 * Name: A.A.S.Ranasinghe
 * Parses a network from a file and builds a FlowNetwork.
 */

import java.io.File;
import java.util.Scanner;

public class NetworkParser {
    public static FlowNetwork parse(String filename) throws Exception {
        Scanner scanner = new Scanner(new File(filename));

        // Read number of nodes
        if (!scanner.hasNextInt()) {
            throw new Exception("Invalid input: Number of nodes expected");
        }
        int numNodes = scanner.nextInt();
        FlowNetwork network = new FlowNetwork(numNodes);

        // Read edges
        while (scanner.hasNextInt()) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int capacity = scanner.nextInt();

            // Validate input
            if (src < 0 || src >= numNodes || dest < 0 || dest >= numNodes || capacity < 0) {
                throw new Exception("Invalid edge: " + src + " " + dest + " " + capacity);
            }

            network.addEdge(src, dest, capacity);
        }

        scanner.close();
        return network;
    }
}