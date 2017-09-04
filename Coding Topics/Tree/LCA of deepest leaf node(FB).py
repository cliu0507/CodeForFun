LCA of deepest leaf node(FB)

给一个 二叉树 ， 求最深节点的最小公共父节点
	1	
  2   3
 6    return 3.
 
 		1
	2  		3
4     	  5    6   retrun 1. 

返回的时候返回lca和depth，每个node如果有大于一个子节点的depth相同就返回这个node，如果有一个子节点depth更深就返回个子节点

class TreeNode(object):
	def __init__(self,x):
		self.left = None
		self.right = None
		self.val = x

class Solution(object):
	def LCA_deepest_leaf_node(self,root):
		if root == None:
			return None
		if depth(root.left) > depth(root.right):
			return LCA_deepest_leaf_node(root.left)
		elif depth(root.left) < depth(root.right):
			return LCA_deepest_leaf_node(root.right)
		else:
			return root

	def depth(self,root):
		if root == None:
			return 0:
		else:
			return max(depth(root.left), depth(root,right)) + 1

