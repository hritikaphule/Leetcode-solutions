from collections import defaultdict

class Solution:
    def equalPairs(self, grid):
        n = len(grid)
        row_map = defaultdict(int)
        
        # Step 1: Store all rows as tuples in map
        for row in grid:
            row_map[tuple(row)] += 1

        count = 0

        # Step 2: For each column, make tuple and check if exists in row_map
        for col in range(n):
            col_tuple = tuple(grid[row][col] for row in range(n))
            count += row_map.get(col_tuple, 0)

        return count
