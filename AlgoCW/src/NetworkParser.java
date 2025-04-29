/*
 * Student ID: 20231160
 * UOW ID: w2051972
 * Name: A.A.S.Ranasinghe
 * Parses a network from a file and builds a FlowNetwork.
 */

import java.io.File;
import java.util.Scanner;

public class NetworkParser {

    /**
     * Parses the given file and constructs a FlowNetwork.
     * @param filename the path to the input file
     * @return the constructed FlowNetwork
     * @throws Exception if the input is invalid or the file cannot be read
     */
    public static FlowNetwork parse(String filename) throws Exception {
        Scanner scanner = new Scanner(new File(filename));

        // Read number of nodes from the first integer
        if (!scanner.hasNextInt()) {
            throw new Exception("Invalid input: Number of nodes expected");
        }
        int numNodes = scanner.nextInt();
        FlowNetwork network = new FlowNetwork(numNodes);

        // Read edges from the file
        while (scanner.hasNextInt()) {
            int src = scanner.nextInt();        // Source node
            int dest = scanner.nextInt();       // Destination node
            int capacity = scanner.nextInt();   // Capacity of the edge

            // Validate edge values
            if (src < 0 || src >= numNodes || dest < 0 || dest >= numNodes || capacity < 0) {
                throw new Exception("Invalid edge: " + src + " " + dest + " " + capacity);
            }

            // Add edge to the network
            network.addEdge(src, dest, capacity);
        }

        scanner.close();
        return network;
    }
}