from typing import List

class Solution:
    def findMaxFish(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        visited = [[False]*n for _ in range(m)]
        directions = [(-1,0),(1,0),(0,-1),(0,1)]

        def dfs(r, c):
            if not (0 <= r < m and 0 <= c < n):
                return 0
            if visited[r][c] or grid[r][c] == 0:
                return 0

            visited[r][c] = True
            total = grid[r][c]
            for dr, dc in directions:
                total += dfs(r + dr, c + dc)
            return total

        max_fish = 0
        for r in range(m):
            for c in range(n):
                if grid[r][c] > 0 and not visited[r][c]:
                    max_fish = max(max_fish, dfs(r, c))

        return max_fish
