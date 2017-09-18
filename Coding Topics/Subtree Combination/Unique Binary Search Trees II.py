Unique Binary Search Trees II


Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
	\       /     /      / \      \
	 3     2     1      1   3      2
	/     /       \                 \
   2     1         2                 3


# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
	def generateTrees(self, n):
		"""
		:type n: int
		:rtype: List[TreeNode]
		"""
		if n == 0:
			return []
		return self.build(1,n)


	def build(self,left,right):
		nodeList = []
		if left == right:
			nodeList.append(TreeNode(left))
			return nodeList
		for i in range(left,right+1):
			leftNodeList = self.build(left,i-1)
			rightNodeList = self.build(i+1,right)
			for leftNode in leftNodeList:
				for rightNode in rightNodeList:
					root = TreeNode(i)
					root.left = leftNode
					root.right = rightNode
					nodeList.append(root)
		if len(nodeList) == 0:
			nodeList.append(None)
		return nodeList