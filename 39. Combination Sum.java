import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, int[] candidates, int target, int start) {
        // Base case: target is 0, add the current combination to the result
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Iterate over the candidates
        for (int i = start; i < candidates.length; i++) {
            // Skip if the current candidate exceeds the target
            if (candidates[i] > target) continue;

            // Choose the current candidate
            current.add(candidates[i]);

            // Recurse with the reduced target and same index (allow reuse of the same number)
            backtrack(result, current, candidates, target - candidates[i], i);

            // Backtrack by removing the last added element
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1
        int[] candidates1 = {2, 3, 6, 7};
        int target1 = 7;
        System.out.println("Combinations for candidates = [2, 3, 6, 7] and target = 7:");
        System.out.println(solution.combinationSum(candidates1, target1));

        // Example 2
        int[] candidates2 = {2, 3, 5};
        int target2 = 8;
        System.out.println("Combinations for candidates = [2, 3, 5] and target = 8:");
        System.out.println(solution.combinationSum(candidates2, target2));

        // Example 3
        int[] candidates3 = {2};
        int target3 = 1;
        System.out.println("Combinations for candidates = [2] and target = 1:");
        System.out.println(solution.combinationSum(candidates3, target3));
    }
}
