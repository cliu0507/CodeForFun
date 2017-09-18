Closest Binary Search Tree Value

Question:
Given a non-empty binary search tree and a target value, 
find the value in the BST that is closest to the target



A simple solution for this problem is to store Inorder traversal of given binary search tree 
in an auxiliary array and then by taking absolute difference of each element find the node having minimum absolute difference 
with given target value K in linear time.


An efficient solution for this problem is to take advantage of characteristics of BST. Here is the algorithm to solve this problem :

If target value K is present in given BST, then it’s the node having minimum absolute difference.
If target value K is less than the value of current node then move to the left child.
If target value K is greater than the value of current node then move to the right child.





Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:

Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 

Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

Hint:

1. Consider implement these two helper functions:
　　i. getPredecessor(N), which returns the next smaller node to N.
　　ii. getSuccessor(N), which returns the next larger node to N.
2. Try to assume that each node has a parent pointer, it makes the problem much easier.
3. Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
4. You would need two stacks to track the path in finding predecessor and successor node separately.