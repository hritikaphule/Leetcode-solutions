class Solution:
    def cherryPickup(self, grid: list[list[int]]) -> int:
        from functools import lru_cache

        n = len(grid)

        @lru_cache(None)
        def dp(r1, c1, r2):
            c2 = r1 + c1 - r2
            if (r1 >= n or c1 >= n or r2 >= n or c2 >= n or 
                grid[r1][c1] == -1 or grid[r2][c2] == -1):
                return float('-inf')

            if r1 == c1 == r2 == c2 == n - 1:
                return grid[r1][c1]

            # Collect cherry from r1,c1 and r2,c2, avoid double count if same
            result = grid[r1][c1]
            if r1 != r2 or c1 != c2:
                result += grid[r2][c2]

            # Explore 4 possible moves
            result += max(
                dp(r1 + 1, c1, r2 + 1),   # both go down
                dp(r1, c1 + 1, r2),       # person1 right, person2 down
                dp(r1 + 1, c1, r2),       # person1 down, person2 right
                dp(r1, c1 + 1, r2 + 1)    # both go right
            )
            return result

        ans = dp(0, 0, 0)
        return max(ans, 0)
