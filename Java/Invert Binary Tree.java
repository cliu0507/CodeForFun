/*
Question:
Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9
to
     4
   /   \
  7     2
 / \   / \
9   6 3   1


*/


Solution1: Recursive way:
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null)
            return null;
        else
        {
            TreeNode tempnode;
            tempnode = invertTree(root.left);
            root.left = invertTree(root.right);
            root.right = tempnode;
            return root;
        }
    }
}


Solution: BFS 
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null)
        {
            return null;
        }
        else
        {
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.add(root);
            TreeNode node;
            while(!queue.isEmpty())
            {
                int width = queue.size();
                for(int i = 0 ; i < width ; i++)
                {
                    node = queue.remove();
                    TreeNode temp = node.left;
                    node.left = node.right;   //Two additional lines of node swapping, others are the same as typical BFS
                    node.right = temp;
                    if(node.left!=null)
                    {
                        queue.add(node.left);
                    }
                    if(node.right!=null)
                    {
                        queue.add(node.right);
                    }
                }
            }
            return root;
        }
    }
}


Solution:DFS
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null)
        {
            return null;
        }
        else
        {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(root);
            
            while(!stack.isEmpty())
            {
                TreeNode node = stack.pop();
                TreeNode temp = node.left;
                node.left = node.right; //Three additional line compared with pure DFS
                node.right = temp;
                if(node.left!=null)
                {
                    stack.push(node.left);
                }
                if(node.right!=null)
                {
                    stack.push(node.right);
                }
            }
            return root;
        }
    }
}


Solution DFS terser version from discussion
//Also push null into stack

    public class Solution {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            Stack<TreeNode> stack1 = new Stack<>();
            Stack<TreeNode> stack2 = new Stack<>();
            stack1.push(p);
            stack2.push(q);
            while(!stack1.isEmpty() && !stack2.isEmpty()) {
                TreeNode n1 = stack1.pop();
                TreeNode n2 = stack2.pop();
                if(n1 == null && n2 == null) continue;
                if(n1 == null || n2 == null || n1.val != n2.val) return false;
                stack1.push(n1.right);
                stack2.push(n2.right);
                stack1.push(n1.left);
                stack2.push(n2.left);
            }
            return true;
        }
    } 



Note:

//1. Recursive solution is naive way. But BFS and DFS are also not hard. Only need to swap left and right child nodes in loop
