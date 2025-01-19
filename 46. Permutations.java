import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, int[] nums) {
        // Base case: if the current permutation has all the elements
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Iterate over all numbers in the array
        for (int num : nums) {
            // Skip if the number is already in the current permutation
            if (current.contains(num)) continue;

            // Add the number to the current permutation
            current.add(num);

            // Recurse to build the rest of the permutation
            backtrack(result, current, nums);

            // Backtrack by removing the last added number
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1
        int[] nums1 = {1, 2, 3};
        System.out.println("Permutations for nums = [1, 2, 3]:");
        System.out.println(solution.permute(nums1));

        // Example 2
        int[] nums2 = {0, 1};
        System.out.println("Permutations for nums = [0, 1]:");
        System.out.println(solution.permute(nums2));

        // Example 3
        int[] nums3 = {1};
        System.out.println("Permutations for nums = [1]:");
        System.out.println(solution.permute(nums3));
    }
}
