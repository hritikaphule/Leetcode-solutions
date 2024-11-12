public class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;

        int rows = board.length;
        int cols = board[0].length;

        // Step 1: Mark all 'O's connected to borders with a temporary marker '*'
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') dfs(board, i, 0);         // Left border
            if (board[i][cols - 1] == 'O') dfs(board, i, cols - 1); // Right border
        }
        for (int j = 0; j < cols; j++) {
            if (board[0][j] == 'O') dfs(board, 0, j);         // Top border
            if (board[rows - 1][j] == 'O') dfs(board, rows - 1, j); // Bottom border
        }

        // Step 2: Capture all surrounded regions and revert the temporary markers
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';  // Captured region
                } else if (board[i][j] == '*') {
                    board[i][j] = 'O';  // Revert temporary marker
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O') {
            return;
        }
        board[i][j] = '*'; // Mark the cell as visited to prevent capture
        dfs(board, i + 1, j); // Down
        dfs(board, i - 1, j); // Up
        dfs(board, i, j + 1); // Right
        dfs(board, i, j - 1); // Left
    }
}
