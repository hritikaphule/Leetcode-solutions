import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightView = new ArrayList<>();
        if (root == null) return rightView;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            Integer rightmost = null;
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                rightmost = node.val;
                
                // Add children to queue for next level
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            // Add the last node of this level to rightView
            rightView.add(rightmost);
        }
        
        return rightView;
    }
}
