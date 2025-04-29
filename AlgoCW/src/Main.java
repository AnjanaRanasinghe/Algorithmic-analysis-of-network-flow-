/*
 * Student ID: 20231160
 * UOW ID: w2051972
 * Name: A.A.S.Ranasinghe
 * Coursework: Algorithmic Analysis of Network Flow
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<File> benchmarkFiles = getBenchmarkFiles();

        boolean continueRunning = true;
        while (continueRunning) {
            // Display menu
            System.out.println("\n=== Maximum Flow Benchmark Tester ===");
            System.out.println("Available benchmark files:");
            for (int i = 0; i < benchmarkFiles.size(); i++) {
                System.out.printf("%d: %s%n", i + 1, benchmarkFiles.get(i).getName());
            }
            System.out.println("\nOptions:");
            System.out.println("Enter a number (1-" + benchmarkFiles.size() + ") to select a file");
            System.out.println("Enter 'q' to quit");

            // Get user input for file selection
            System.out.print("Your choice: ");
            String input = scanner.nextLine().trim();

            // Check for quit
            if (input.equalsIgnoreCase("q")) {
                System.out.println("Exiting program.");
                continueRunning = false;
                continue;
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
                FordFulkerson ff = new FordFulkerson(network);

                // Compute and print maximum flow
                int maxFlow = ff.computeMaxFlow();
                System.out.println("Maximum Flow: " + maxFlow);

                // Print incremental steps
                for (String step : ff.getSteps()) {
                    System.out.println(step);
                }

                // Ask if the user wants to continue
                while (true) {
                    System.out.println("\nWant to continue? (y/n): ");
                    String continueChoice = scanner.nextLine().trim().toLowerCase();
                    if (continueChoice.equals("y")) {
                        break; // Continue the main loop
                    } else if (continueChoice.equals("n")) {
                        System.out.println("Exiting program.");
                        continueRunning = false;
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter 'y' or 'n'.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or 'q' to quit.");
            } catch (Exception e) {
                System.err.println("Error processing file: " + e.getMessage());
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

        // Custom sorting: bridge_1 to bridge_19, then ladder_1 to ladder_20
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File f1, File f2) {
                String name1 = f1.getName();
                String name2 = f2.getName();

                // Extract prefix (bridge or ladder)
                String prefix1 = name1.split("_")[0];
                String prefix2 = name2.split("_")[0];

                // If prefixes differ, bridge comes before ladder
                if (!prefix1.equals(prefix2)) {
                    return prefix1.compareTo(prefix2); // "bridge" < "ladder"
                }

                // If prefixes are the same, sort by the numeric part
                int num1 = extractNumber(name1);
                int num2 = extractNumber(name2);
                return Integer.compare(num1, num2);
            }

            // Extract the numeric part from the filename (e.g., "1" from "bridge_1.txt")
            private int extractNumber(String filename) {
                String numberPart = filename.split("_")[1].replace(".txt", "");
                return Integer.parseInt(numberPart);
            }
        });

        List<File> fileList = new ArrayList<>(Arrays.asList(files));

        // Ensure we have exactly 39 files (19 bridge + 20 ladder)
        if (fileList.size() != 39) {
            System.err.println("Warning: Expected 39 benchmark files (bridge_1.txt to bridge_19.txt, ladder_1.txt to ladder_20.txt), but found " + fileList.size());
        }

        return fileList;
    }
}