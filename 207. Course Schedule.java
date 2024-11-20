import java.util.*;

public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Adjacency list representation of the graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Array to keep track of the in-degree of each node
        int[] inDegree = new int[numCourses];

        // Build the graph and calculate in-degrees
        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int prerequisite = prereq[1];
            graph.get(prerequisite).add(course);
            inDegree[course]++;
        }

        // Queue to perform BFS
        Queue<Integer> queue = new LinkedList<>();

        // Add all courses with in-degree 0 to the queue
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // Process the graph
        int count = 0; // Count of courses that can be taken
        while (!queue.isEmpty()) {
            int current = queue.poll();
            count++;

            // Reduce the in-degree of neighbors
            for (int neighbor : graph.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // If count matches numCourses, all courses can be completed
        return count == numCourses;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.canFinish(2, new int[][]{{1, 0}})); // Output: true
        System.out.println(solution.canFinish(2, new int[][]{{1, 0}, {0, 1}})); // Output: false
    }
}
