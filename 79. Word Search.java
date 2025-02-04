class Solution {
    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        // Iterate through each cell in the board to find the first character of the word
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (backtrack(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backtrack(char[][] board, String word, int i, int j, int index) {
        // Base case: if the entire word is matched
        if (index == word.length()) {
            return true;
        }

        // Boundary check: out of bounds or character mismatch
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index)) {
            return false;
        }

        // Temporarily mark the cell as visited
        char temp = board[i][j];
        board[i][j] = '#';

        // Explore all four possible directions (up, down, left, right)
        boolean found = backtrack(board, word, i + 1, j, index + 1) || 
                        backtrack(board, word, i - 1, j, index + 1) || 
                        backtrack(board, word, i, j + 1, index + 1) || 
                        backtrack(board, word, i, j - 1, index + 1);

        // Restore the cell back to its original character
        board[i][j] = temp;

        return found;
    }
}            