package com.jsoniter;

public class BranchCoverageDIY {
    
    private static final int NUM_BRANCHES = 10; // TODO: CHANGE TO ACTUAL COUNT
    private static boolean[] branchesReached = new boolean[NUM_BRANCHES];

    public static boolean[] getBranchesReached() {
        return branchesReached;
    }

    public static void setBranchReached(int branchID) {
        if (branchID >= 0 && branchID < NUM_BRANCHES) {
            branchesReached[branchID] = true;
        } else {
            throw new IllegalArgumentException("Invalid branch ID");
        }
    }
}
