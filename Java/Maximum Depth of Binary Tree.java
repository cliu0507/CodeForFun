/*
Question:

Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.


*/



Recursive Solution：

public class Solution {
    public int maxDepth(TreeNode root) {
        if(root==null)
            return 0;
        else
            return (1+Math.max(maxDepth(root.left),maxDepth(root.right)));
    }
}


/*DFS solution:
 DFS is hard to understand. If we don't record the depth of the tree, one stack should be ok. But now we are recording depth, so we need a helper stack to store each node's depth, then we pick the max of depth
 
 DFS FOR STACKS 
 http://algorithms.tutorialhorizon.com/depth-first-searchtraversal-in-binary-tree/
 
 */
 
public class Solution {
    public int maxDepth(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Stack<Integer> sub = new Stack<Integer>();
        if(root == null)
            return 0;
        else
        {
            int result=0;
            stack.push(root);
            sub.push(1);
            int depth = 0;
            int max_depth = 1;
            while(!stack.isEmpty())
            {
                TreeNode node = stack.pop();
                depth = (int)sub.pop();
                
                if(depth > max_depth)
                {
                    max_depth=depth;
                }
                
                if(node.left!=null)
                {
                    stack.push(node.left);
                    sub.push(depth+1);
                }    
                if(node.right!=null)
                {
                    stack.push(node.right);
                    sub.push(depth+1);
                }
            }
            return max_depth;
        }
    }
}
 

/*BFS solution: 

Put each level into queue, iterate each level(while loop), and do a for-loop for each node in each level.Once all nodes in one layers are done, Depth++

Note: Queue will store nodes in the same level ==> very typical BFS


*/

public class Solution {
    public int maxDepth(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        if (root == null)
            return 0;
        else
        {
            int result=0;
            int width;
            queue.add(root);
            while(!queue.isEmpty())
            {
                width=queue.size();
                for(int i = 0 ; i < width ; i++)
                {
                    TreeNode node = queue.remove();
                    if(node.left!=null)
                        queue.add(node.left);
                    if(node.right!=null)
                        queue.add(node.right);
        
                }
                result++;
            }
            return result;
        }
    }
}








Note:

1....
2....
3....