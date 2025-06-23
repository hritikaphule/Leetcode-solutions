class Solution:
    def maxProfit(self, n, present, future, hierarchy, budget):
        from collections import defaultdict

        tree = defaultdict(list)
        for u, v in hierarchy:
            tree[u - 1].append(v - 1)

        dp0 = [None] * n  # parent didn't buy u
        dp1 = [None] * n  # parent bought u, u gets discount

        def merge(dp_parent, dp_child):
            merged = dp_parent[:]
            for b in range(budget, -1, -1):
                for c in range(1, b + 1):
                    if c < len(dp_child):
                        merged[b] = max(merged[b], dp_parent[b - c] + dp_child[c])
            return merged

        def dfs(u):
            dp0[u] = [0] * (budget + 1)
            dp1[u] = [float('-inf')] * (budget + 1)

            # Initial: Buy u
            p_full = present[u]
            profit_full = future[u] - p_full
            if p_full <= budget:
                dp0[u][p_full] = profit_full

            p_disc = present[u] // 2
            profit_disc = future[u] - p_disc
            if p_disc <= budget:
                dp1[u][p_disc] = profit_disc

            for v in tree[u]:
                dfs(v)

                # Merge into dp0[u]: parent didn't buy u
                child_best = [max(dp0[v][i], dp1[v][i]) for i in range(budget + 1)]
                dp0[u] = merge(dp0[u], child_best)

                # Merge into dp1[u]: parent bought u → child gets discount
                dp1[u] = merge(dp1[u], dp1[v])

        dfs(0)
        return max(max(dp0[0]), max(dp1[0]))
