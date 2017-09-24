/*
Question: 112. Path Sum
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.


*/

DFS:

public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if(root==null)
            return false;
        else
        {
            stack.push(root);
            while(!stack.empty())
            {
                TreeNode curNode = stack.pop();
                int val = curNode.val;
                if(curNode.left!=null)
                {
                    curNode.left.val = curNode.val+curNode.left.val;
                    stack.push(curNode.left);
                }
                if(curNode.right!=null)
                {
                    curNode.right.val = curNode.val + curNode.right.val;
                    stack.push(curNode.right);
                }
                if(curNode.left==null && curNode.right == null)
                {
                    if(val == sum)
                        return true;
                }
            }
            return false;
        }
    }
}


Recursive Method:
Solution 2:
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
    
        if(root.left == null && root.right == null && sum - root.val == 0) return true;
    
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}





Note:

1....
2....
3....