LCA of a Binary Tree with parent pointer


Given values of two nodes in a Binary Tree, find the Lowest Common Ancestor (LCA). It may be assumed that both nodes exist in the tree.


Finding LCA becomes easy when parent pointer is given 
as we can easily find all ancestors of a node using parent pointer.



Below are steps to find LCA.

Create an empty hash table.
Insert n1 and all of its ancestors in hash table.
Check if n2 or any of its ancestors exist in hash table, if yes return the first existing ancestor.

class TreeNode(object):
	def __init__(self,x):
		self.val = x
        self.left = None
        self.right = None
        self.parent = None

class Solution(object):
	def LCA_with_parent(self, p , q):
		path = []
		node = p
		while(node!=None):
			path.insert(0,node)
			node = node.parent
		node = q 
		while(node!=None):
			if node in path:
				return node
			node = node.parent
		return None
