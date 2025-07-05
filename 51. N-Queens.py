from typing import List

class Solution:
    def solveNQueens(self, n: int) -> List[List[str]]:
        def backtrack(row):
            if row == n:
                board = []
                for r in range(n):
                    line = ['.'] * n
                    line[queens[r]] = 'Q'
                    board.append("".join(line))
                result.append(board)
                return

            for col in range(n):
                if col in cols or (row - col) in diag1 or (row + col) in diag2:
                    continue

                queens[row] = col
                cols.add(col)
                diag1.add(row - col)
                diag2.add(row + col)

                backtrack(row + 1)

                # backtrack
                cols.remove(col)
                diag1.remove(row - col)
                diag2.remove(row + col)

        result = []
        queens = [-1] * n
        cols = set()
        diag1 = set()  # row - col
        diag2 = set()  # row + col
        backtrack(0)
        return result
