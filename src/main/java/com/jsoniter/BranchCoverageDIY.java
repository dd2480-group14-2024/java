package com.jsoniter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BranchCoverageDIY {
    
    private static final int[] NUM_BRANCHES = {10, 10, 10, 10, 10};
    
    private static Map<Integer, Set<Integer>> branchCoverageMap = new HashMap<>(); // key: function ID, value: set containing branches reached.

    public static void setBranchReached(int function, int branchID) {
        branchCoverageMap
                .computeIfAbsent(function, k -> new HashSet<>())
                .add(branchID);
    }

    public static void writeResultsToFile(int function) {
        Path directoryPath = Paths.get(".", "results");
        Path filePath = Paths.get(".", "results", function + ".txt");
        try {
            Files.createDirectories(directoryPath);
            try (FileWriter writer = new FileWriter(filePath.toFile())) {
                writer.write("Results\n");
                writer.write("Total branches for function " + function + ": " + NUM_BRANCHES[function-1] + "\n");
                if (branchCoverageMap.get(function) != null) {
                    writer.write("Branches taken: " + branchCoverageMap.get(function).size()  + "\n");
                    writer.write("Percentage: " + ((double) branchCoverageMap.get(function).size() / NUM_BRANCHES[function-1] * 100.0)  + " %\n");
                } else {
                    writer.write("Branches taken: " + 0 + "\n");
                    writer.write("Percentage: " + 0  + " %\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
