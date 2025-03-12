import java.util.PriorityQueue;
import java.util.Arrays;

class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;

        // Step 1: Store and sort projects based on capital
        int[][] projects = new int[n][2];
        for (int i = 0; i < n; i++) {
            projects[i] = new int[]{capital[i], profits[i]};
        }
        Arrays.sort(projects, (a, b) -> Integer.compare(a[0], b[0])); // Sort by capital
        
        // Step 2: Max heap to pick most profitable projects
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a)); 
        
        int i = 0;
        while (k-- > 0) {
            // Add all feasible projects to the max heap
            while (i < n && projects[i][0] <= w) {
                maxHeap.offer(projects[i][1]); // Store profit
                i++;
            }

            // If no project can be taken, break
            if (maxHeap.isEmpty()) break;

            // Pick the most profitable project
            w += maxHeap.poll();
        }

        return w;
    }
}
