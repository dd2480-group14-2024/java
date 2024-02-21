package com.jsoniter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BranchCoverageDIY {
    
    private static final int[] NUM_BRANCHES = {10, 10, 10, 10, 10}; // TODO: update with actual values
    
    private static Map<Integer, Set<Integer>> branchCoverageMap = new HashMap<>(); // key: function ID, value: set containing branches reached.

    public static void setBranchReached(int function, int branchID) {
        branchCoverageMap
                .computeIfAbsent(function, k -> new HashSet<>())
                .add(branchID);
    }

    public static void writeResultsToFile(int function) {
        Path filePath = Paths.get(".", "test", function + ".txt");

        try (FileWriter writer = new FileWriter(filePath.toFile())) {
            writer.write("Results");
            writer.write("Total branches for function " + function + ": " + NUM_BRANCHES[function]);
            writer.write("Branches taken: " + branchCoverageMap.get(function).size());
            writer.write("Percentage: " + ((double) branchCoverageMap.get(function).size() / NUM_BRANCHES[function] * 100.0));
            System.out.println("File written successfully at: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
