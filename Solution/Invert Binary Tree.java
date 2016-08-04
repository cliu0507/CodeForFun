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



Note:

//1. Recursive solution is naive way. But BFS and DFS are also not hard. Only need to swap left and right child nodes in loop
