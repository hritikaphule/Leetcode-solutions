class Solution {
    private int count = 0;

    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];        // Tracks columns that are occupied
        boolean[] diag1 = new boolean[2 * n];   // Tracks major diagonals (\)
        boolean[] diag2 = new boolean[2 * n];   // Tracks minor diagonals (/)

        backtrack(0, n, cols, diag1, diag2);
        return count;
    }

    private void backtrack(int row, int n, boolean[] cols, boolean[] diag1, boolean[] diag2) {
        if (row == n) {  // All queens are placed successfully
            count++;
            return;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row - col + n;  // Major diagonal index
            int d2 = row + col;      // Minor diagonal index

            // If the column or diagonals are occupied, skip this position
            if (cols[col] || diag1[d1] || diag2[d2]) continue;

            // Place the queen
            cols[col] = diag1[d1] = diag2[d2] = true;

            // Recurse to the next row
            backtrack(row + 1, n, cols, diag1, diag2);

            // Backtrack: Remove the queen
            cols[col] = diag1[d1] = diag2[d2] = false;
        }
    }
}    