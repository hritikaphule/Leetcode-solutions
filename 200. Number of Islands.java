public class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int numIslands = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {  // Start of a new island
                    numIslands++;
                    dfs(grid, i, j);  // Perform DFS to mark the entire island
                }
            }
        }
        return numIslands;
    }

    private void dfs(char[][] grid, int i, int j) {
        // Boundary and water checks
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }

        // Mark the current cell as visited by setting it to '0'
        grid[i][j] = '0';

        // Recursively check all four adjacent directions
        dfs(grid, i + 1, j);  // Down
        dfs(grid, i - 1, j);  // Up
        dfs(grid, i, j + 1);  // Right
        dfs(grid, i, j - 1);  // Left
    }
}
