/*
Question:
113. Path Sum II
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
*/

Best Sol: DFS:

public class Solution {
    private List<List<Integer>> resultList = new ArrayList<List<Integer>>();
    
    public void pathSumInner(TreeNode root, int sum, Stack<Integer>path) {
        path.push(root.val);
        if(root.left == null && root.right == null)
            if(sum == root.val) resultList.add(new ArrayList<Integer>(path));
        if(root.left!=null) pathSumInner(root.left, sum-root.val, path);
        if(root.right!=null)pathSumInner(root.right, sum-root.val, path);
        path.pop();
    }
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root==null) return resultList;
        Stack<Integer> path = new Stack<Integer>();
        pathSumInner(root, sum, path);
        return resultList;
    }
}

My submission: DFS:
public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        if(root == null) return list;
        helper(root,sum,path,list);
        return list;
        
    }
    
    public void helper(TreeNode root,int sum,List<Integer> path, List<List<Integer>> list)
    {
        path.add(root.val);
        if(root.left == null && root.right == null)
        {
            if(root.val == sum)
            {
                list.add(new ArrayList<Integer>(path));
            }    
        }
        if(root.left!=null)
            helper(root.left,sum-root.val,path, list);
        if(root.right!=null)
            helper(root.right,sum-root.val,path,list);
        path.remove(path.size()-1);
    }
}


Another My Solution accepted: Not good, very complicated. backtracking by hashmap
Use a hashmap to store the parent TreeNode (Note need to handle the incremental treenode value, need to convert them back, not cumulatively)

public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null) 
            return result;
        else
        {
            HashMap<TreeNode,TreeNode> hash = new HashMap<TreeNode,TreeNode>();
            hash.put(root,null);
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(root);
            while(!stack.isEmpty())
            {
                TreeNode curNode = stack.pop();
                int val = curNode.val;
                if(curNode.left != null)
                {
                    curNode.left.val = curNode.val + curNode.left.val;
                    stack.push(curNode.left);
                    hash.put(curNode.left,curNode);
                }
                if(curNode.right!=null)
                {
                    curNode.right.val = curNode.val + curNode.right.val;
                    stack.push(curNode.right);
                    hash.put(curNode.right,curNode);
                }
                if(curNode.left == null && curNode.right == null && curNode.val == sum)
                {
                    
                    TreeNode parent = hash.get(curNode);
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(0,curNode.val);
                    while(parent!=null)
                    {
                        list.add(0, parent.val);
                        parent = hash.get(parent);
                    }
                    result.add(list);
                    for(int i = list.size()-1 ; i >=1 ; i--)
                    {
                        list.set(i, list.get(i) - list.get(i-1));
                    }
                }
            }
            return result;
        }
    }
}

Solution 2:






Note:

1....
2....
3....