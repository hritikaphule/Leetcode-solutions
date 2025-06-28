from collections import defaultdict, deque

class Solution:
    def minScore(self, n: int, roads: List[List[int]]) -> int:
        graph = defaultdict(list)
        for u, v, d in roads:
            graph[u].append((v, d))
            graph[v].append((u, d))

        visited = set()
        min_score = float('inf')
        queue = deque([1])

        while queue:
            node = queue.popleft()
            if node in visited:
                continue
            visited.add(node)

            for neighbor, dist in graph[node]:
                min_score = min(min_score, dist)
                if neighbor not in visited:
                    queue.append(neighbor)

        return min_score
