import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), 1, n, k);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, int start, int n, int k) {
        // Base case: if the current combination has k elements, add it to the result list
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Iterate over numbers from start to n
        for (int i = start; i <= n; i++) {
            current.add(i); // Add the current number to the combination
            backtrack(result, current, i + 1, n, k); // Recurse with the next number
            current.remove(current.size() - 1); // Backtrack by removing the last number
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Example 1
        int n1 = 4, k1 = 2;
        System.out.println("Combinations for n = " + n1 + ", k = " + k1 + ":");
        System.out.println(solution.combine(n1, k1));

        // Example 2
        int n2 = 1, k2 = 1;
        System.out.println("Combinations for n = " + n2 + ", k = " + k2 + ":");
        System.out.println(solution.combine(n2, k2));
    }
}
