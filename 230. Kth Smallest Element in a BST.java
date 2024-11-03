class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        int count = 0;
        
        while (current != null || !stack.isEmpty()) {
            // Move to the leftmost node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            current = stack.pop();
            count++;
            
            // If we've reached the k-th smallest element, return its value
            if (count == k) {
                return current.val;
            }
            
            // Move to the right node
            current = current.right;
        }
        
        // In case k is out of bounds (not expected as per constraints)
        return -1;
    }
}
