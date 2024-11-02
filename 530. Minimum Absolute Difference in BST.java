class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public int getMinimumDifference(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        Integer prevValue = null;
        int minDifference = Integer.MAX_VALUE;
        
        while (current != null || !stack.isEmpty()) {
            // Traverse to the leftmost node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            current = stack.pop();
            
            // Calculate min difference with the previous value
            if (prevValue != null) {
                minDifference = Math.min(minDifference, current.val - prevValue);
            }
            
            // Update prevValue to the current node's value
            prevValue = current.val;
            
            // Move to the right node
            current = current.right;
        }
        
        return minDifference;
    }
}
