import java.util.*;

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return result;
        
        // Min heap storing (sum, index in nums1, index in nums2)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> (nums1[a[0]] + nums2[a[1]])));

        // Push the first k pairs (nums1[i], nums2[0]) into heap
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            minHeap.offer(new int[]{i, 0}); // Store index pairs
        }

        // Extract k smallest pairs
        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] indices = minHeap.poll();
            int i = indices[0], j = indices[1];

            result.add(Arrays.asList(nums1[i], nums2[j]));

            // Add the next pair (i, j+1) if available
            if (j + 1 < nums2.length) {
                minHeap.offer(new int[]{i, j + 1});
            }
        }

        return result;
    }
}
