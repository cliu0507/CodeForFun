Count Univalue Subtrees

http://www.geeksforgeeks.org/find-count-of-singly-subtrees/

Given a binary tree, write a program to count the number of Single Valued Subtrees. A Single Valued Subtree is one in which all the nodes have same value. Expected time complexity is O(n).

Example:

Input: root of below tree
              5
             / \
            1   5
           / \   \
          5   5   5
Output: 4
There are 4 subtrees with single values.


Input: root of below tree
              5
             / \
            4   5
           / \   \
          4   4   5                
Output: 5
There are five subtrees with single values.


DFS: Subtree Tuple
return 
1. number of univalue subtree
2. univalue
3. whether the root tree is univalue tree

class Solution(object):
	def CountUniSub(self,root):

		result,_,_ = self.helper(root)
		return result

	def helper(root):
		if root == None:
			return 0, None, True
		else:
			numLeftSubtree, valLeft, leftFlag = self.helper(root.left)
			numRightSubtree, valRight, rightFlag = self.helper(root.right)
			if (leftFlag == True and rightFlag == True) and ((valLeft in [root.val,None ]) and (valRight in [root.val,None])):
				return numLeftSubtree+numRightSubtree+1, root.val, True
			else:
				return numLeftSubtree+numRightSubtree, root.val, False



