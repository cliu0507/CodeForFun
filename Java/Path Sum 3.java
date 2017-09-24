/*
Question: 437. Path Sum III  
You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11


*/


My First Submission: (Wrong!!! , A very subtle bug)
Bugs are hard to find:cannot keep correct sum
This code is not intuitive

public class Solution {
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return 0;
        int left = 0;
        int right = 0;
        int lefthead = 0;
        int righthead = 0;
        int tail = 0;
        if(root.left != null)
        {
            left = pathSum(root.left,sum-root.val);
            lefthead = pathSum(root.left,sum);
        }
        if(root.right != null)
        {
            right = pathSum(root.right,sum-root.val);
            righthead = pathSum(root.right,sum);
        }
        if(root.val == sum)
            tail++;
        return left+lefthead+right+righthead+tail;
    }
}


Correct Recursive Code:
Solution 2:


findpath function is to find path starting from a node
public class Solution {
    public int pathSum(TreeNode root, int sum) {
        if(root == null)
            return 0;
        return findPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);// the way to guarantee that global sum can be maintained to each node
    }
    
    public int findPath(TreeNode root, int sum){
        int res = 0;
        if(root == null)
            return res;
        if(sum == root.val)
            res++;
        res += findPath(root.left, sum - root.val);
        res += findPath(root.right, sum - root.val);
        return res;
    }
   
}


Another Method:  Prefix sum method
So the idea is similar as Two sum, using HashMap to store ( key : the prefix sum, value : how many ways get to this prefix sum) , and whenever reach a node, we check if prefix sum - target exists in hashmap or not, if it does, we added up the ways of prefix sum - target into res.
For instance : in one path we have 1,2,-1,-1,2, then the prefix sum will be: 1, 3, 2, 1, 3, lets say we want to find target sum is 2, then we will have {2}, {1, 2, -1, -1, 2}, { 2, -1, -1, 2} ways.

I used global variable count, but obviously we can avoid global variable by passing the count from bottom up. The time complexity is O(n). This is my first post in discuss, open to any improvement or criticism. :)

 public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap();
        preSum.put(0,1);
        return helper(root, 0, sum, preSum);
    }
    
    public int helper(TreeNode root, int sum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) {
            return 0;
        }
        
        sum += root.val;
        int res = preSum.getOrDefault(sum - target, 0);
        preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        
        res += helper(root.left, sum, target, preSum) + helper(root.right, sum, target, preSum);
        preSum.put(sum, preSum.get(sum) - 1);
        return res;
    }

Note:

1....
2....
3....