/*
Question:
94. Binary Tree Inorder Traversal   
Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree [1,null,2,3],
   1
    \
     2
    /
   3
return [1,3,2].

*/


Solution 1:
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        if(root == null) return list;
        DFS(root,list);
        return list;
    }
    
    public void DFS(TreeNode root , List list)
    {
        
        if(root.left != null)
            DFS(root.left, list);
        list.add(root.val);
        if(root.right!=null)
            DFS(root.right, list);
    }
}


Solution 2: DFS STACK
public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<Integer>();

    Stack<TreeNode> stack = new Stack<TreeNode>();
    TreeNode cur = root;

    while(cur!=null || !stack.empty()){
        while(cur!=null){
            stack.add(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        list.add(cur.val);
        cur = cur.right;
    }

    return list;
}

Solution 3
Morris Traversal------- NO RECURSION NO STACK
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null) return new ArrayList<Integer>();
        List<Integer> res = new ArrayList<Integer>();
        TreeNode pre = null;
        while(root != null){
        	if(root.left == null){
        		res.add(root.val);
        		root = root.right;
        	}else{
        		pre = root.left;
        		while(pre.right != null && pre.right != root){
        			pre = pre.right;
        		}
        		if(pre.right == null){
        			pre.right = root;
        			root = root.left;
        		}else{
        			pre.right = null;
        			res.add(root.val);
        			root = root.right;
        		}
        	}
        }
        return res;
    }
}


https://codeoverflow.wordpress.com/tag/morris-inorder-traversal/

Note:

1....
2....
3....