from typing import List

class Solution:
    def maxWeight(self, n: int, edges: List[List[int]], k: int, t: int) -> int:
        # dp[node][steps] = max weight sum ending at node with `steps` edges
        dp = [[-float('inf')] * (k + 1) for _ in range(n)]

        # Base case: path with 0 edges has weight 0 at every node
        for u in range(n):
            dp[u][0] = 0

        # Build dp for each step from 1 to k
        for step in range(1, k + 1):
            for u, v, w in edges:
                if dp[u][step - 1] != -float('inf'):
                    dp[v][step] = max(dp[v][step], dp[u][step - 1] + w)

        # Find max weight with exactly k edges, strictly less than t
        res = -1
        for u in range(n):
            if dp[u][k] < t:
                res = max(res, dp[u][k])

        return res
