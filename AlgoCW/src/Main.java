/*
 * Student ID: 20231160
 * UOW ID: w2051972
 * Name: A.A.S.Ranasinghe
 * Represents a flow network using an adjacency list.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<File> benchmarkFiles = getBenchmarkFiles();

        while (true) {
            // Display menu
            System.out.println("\n=== Maximum Flow Benchmark Tester ===");
            System.out.println("Available benchmark files:");
            for (int i = 0; i < benchmarkFiles.size(); i++) {
                System.out.printf("%d: %s%n", i + 1, benchmarkFiles.get(i).getName());
            }
            System.out.println("\nOptions:");
            System.out.println("Enter a number (1-" + benchmarkFiles.size() + ") to select a file");
            System.out.println("Enter 'q' to quit");

            // Get user input
            System.out.print("Your choice: ");
            String input = scanner.nextLine().trim();

            // Check for quit
            if (input.equalsIgnoreCase("q")) {
                System.out.println("Exiting program.");
                break;
            }

            // Parse input as a number
            try {
                int choice = Integer.parseInt(input);
                if (choice < 1 || choice > benchmarkFiles.size()) {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + benchmarkFiles.size() + ".");
                    continue;
                }

                // Process the selected file
                File selectedFile = benchmarkFiles.get(choice - 1);
                System.out.println("\nProcessing file: " + selectedFile.getName());
                FlowNetwork network = NetworkParser.parse(selectedFile.getPath());
                EdmondsKarp ek = new EdmondsKarp(network);

                // Compute and print maximum flow
                int maxFlow = ek.computeMaxFlow();
                System.out.println("Maximum Flow: " + maxFlow);

                // Print incremental steps
                for (String step : ek.getSteps()) {
                    System.out.println(step);
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or 'q' to quit.");
            } catch (Exception e) {
                System.err.println("Error processing file: " + e.getMessage());
            }

            System.out.println("\nWant to continue? (y/n): ");
            String scn = scanner.nextLine().trim();

            if (scn.equalsIgnoreCase("y")) {
                continue;
            }

            if (scn.equalsIgnoreCase("n")) {
                System.out.println("Exiting program.");
                break;
            }
        }


        scanner.close();
    }

    // Retrieve and sort benchmark files from the benchmarks folder
    private static List<File> getBenchmarkFiles() {
        File benchmarkDir = new File("benchmarks");
        if (!benchmarkDir.exists() || !benchmarkDir.isDirectory()) {
            System.err.println("Error: 'benchmarks' folder not found in the project directory.");
            System.exit(1);
        }

        File[] files = benchmarkDir.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files == null || files.length == 0) {
            System.err.println("Error: No .txt files found in the benchmarks folder.");
            System.exit(1);
        }

        // Sort files alphabetically
        // Expected order: bridge_1.txt, bridge_2.txt, ..., bridge_19.txt, ladder_1.txt, ..., ladder_20.txt
        Arrays.sort(files, (f1, f2) -> f1.getName().compareTo(f2.getName()));
        List<File> fileList = new ArrayList<>(Arrays.asList(files));

        // Ensure we have exactly 39 files (19 bridge + 20 ladder)
        if (fileList.size() != 39) {
            System.err.println("Warning: Expected 39 benchmark files (bridge_1.txt to bridge_19.txt, ladder_1.txt to ladder_20.txt), but found " + fileList.size());
        }

        return fileList;
    }
}