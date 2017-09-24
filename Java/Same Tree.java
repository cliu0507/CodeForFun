/*
Question:

Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same value.

*/

Recursive Solution
Solution 1:
public boolean isSameTree(TreeNode p, TreeNode q) {
    // base case
    if(p==null || q==null) return p==null && q==null;
    // recursion
    return p.val==q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
}




Breath First Search:
public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p!=null && q!=null)
        {
            Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
            Queue<TreeNode> queue2 = new LinkedList<TreeNode>();
            queue1.add(p);
            queue2.add(q);
            while(!queue1.isEmpty() && !queue2.isEmpty())
            {
                int width1 = queue1.size();
                int width2 = queue2.size();
                if (width1!=width2)
                    return false;
                for(int i = 0 ; i < width1 ; i++)
                {
                    TreeNode node1 = queue1.remove();
                    TreeNode node2 = queue2.remove();
                    if(node1.val!=node2.val)
                        return false;
                    if(node1.left!=null && node2.left!=null)
                    {
                        queue1.add(node1.left);
                        queue2.add(node2.left);
                    }
                    else if(node1.left==null && node2.left==null)
                    {
                        ;
                    }
                    else
                    {
                        return false;
                    }
                
                    if(node1.right!=null && node2.right!=null)
                    {
                        queue1.add(node1.right);
                        queue2.add(node2.right);
                    }
                    else if(node1.right==null && node2.right==null)
                    {
                        ;
                    }
                    else
                    {
                        return false;
                    }
                
                }
            }
            
            if(queue1.isEmpty()!=queue2.isEmpty())
                return false;
            return true;
        }
        else if(p==null && q==null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}





Depth First Search:

/*Idea is that : Two stacks for two trees, in inner loop, each node should have same structures and same value:

node1.left and node2.left must be non-null or both-null, if only one is not null, it means tree has different structures
*/

public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q == null)
            return true;
        else
        {
            Stack<TreeNode> stack1 = new Stack<TreeNode>();
            Stack<TreeNode> stack2 = new Stack<TreeNode>();
            if(p!=null && q!=null)
            {
                stack1.push(p);
                stack2.push(q);
                while(!stack1.isEmpty() && !stack2.isEmpty())
                {
                    TreeNode node1 = stack1.pop();
                    TreeNode node2 = stack2.pop();
                    if(node1.val!=node2.val)
                        return false;
                    if(node1.left!=null && node2.left!=null)
                    {
                        stack1.push(node1.left);
                        stack2.push(node2.left);
                    }
                    else if(!(node1.left==null && node2.left==null ))
                        return false;
                    if(node1.right!=null && node2.right!=null)
                    {
                        stack1.push(node1.right);
                        stack2.push(node2.right);
                    }
                    else if(!(node1.right==null && node2.right==null ))
                        return false;
                }
                if(stack1.isEmpty()!=stack2.isEmpty())
                    return false;
                return true;
            }
            else
                return false;
        }
    }
}



Note:

1. Recursive solution + BFS + DFS
