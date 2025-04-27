/*
 * Student ID: 20231160
 * UOW ID: w2051972
 * Name: A.A.S.Ranasinghe
 * Coursework: Algorithmic Analysis of Network Flow
 */


public class Main {
    public static void main(String[] args) {
        System.out.println("Network Flow Project Setup");
        try {
            // Replace with your input file path
            String filename = "ladder_3.txt";
            FlowNetwork network = NetworkParser.parse(filename);
            EdmondsKarp ek = new EdmondsKarp(network);

            // Compute and print maximum flow
            int maxFlow = ek.computeMaxFlow();
            System.out.println("Maximum Flow: " + maxFlow);

            // Print incremental steps
            for (String step : ek.getSteps()) {
                System.out.println(step);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}