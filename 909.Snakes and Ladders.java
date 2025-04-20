public class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] visited = new boolean[n * n + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0}); // [square number, steps]
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int pos = curr[0];
            int steps = curr[1];

            for (int move = 1; move <= 6; move++) {
                int nextPos = pos + move;
                if (nextPos > n * n) break;
                int[] rc = getRowCol(nextPos, n);
                int r = rc[0], c = rc[1];

                if (board[r][c] != -1) {
                    nextPos = board[r][c]; // Ladder or snake
                }

                if (nextPos == n * n) return steps + 1;

                if (!visited[nextPos]) {
                    visited[nextPos] = true;
                    queue.offer(new int[]{nextPos, steps + 1});
                }
            }
        }

        return -1; // Cannot reach the end
    }

    // Converts a square number to a (row, col) on the board
    private int[] getRowCol(int num, int n) {
        int r = n - 1 - (num - 1) / n;
        int c = (num - 1) % n;
        if ((n - 1 - r) % 2 == 1) {
            c = n - 1 - c; // Reverse direction on odd-numbered rows
        }
        return new int[]{r, c};
    }
}