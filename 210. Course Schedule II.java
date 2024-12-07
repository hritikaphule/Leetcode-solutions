from collections import deque, defaultdict

class Solution:
    def findOrder(self, numCourses: int, prerequisites: list[list[int]]) -> list[int]:
        # Step 1: Create adjacency list and indegree array
        graph = defaultdict(list)
        indegree = [0] * numCourses

        for course, prereq in prerequisites:
            graph[prereq].append(course)
            indegree[course] += 1

        # Step 2: Initialize queue with nodes of indegree 0
        queue = deque([i for i in range(numCourses) if indegree[i] == 0])
        result = []

        # Step 3: Process nodes in topological order
        while queue:
            current = queue.popleft()
            result.append(current)
            for neighbor in graph[current]:
                indegree[neighbor] -= 1
                if indegree[neighbor] == 0:
                    queue.append(neighbor)

        # Step 4: Check if all nodes are processed
        return result if len(result) == numCourses else []
