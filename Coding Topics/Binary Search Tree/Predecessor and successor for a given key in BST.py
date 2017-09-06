predecessor and successor for a given key in BST

class TreeNode(object):
	def __init__(self,key):
		self.left = None
		self.right = None
		self.key = key


class Solution(object):
	
	def findPreSuc(self,root,key):
		predecessor = None
		successor = None
		predecessor,successor = self.findPreSucHelper(root,key,predecessor,successor)
		return predecessor,successor

	def findPreSucHelper(self, root, key, predecessor=None, successor = None):

		if root == None:
			return None,None
		
		if root.key == key:
			if root.left is not None:
				#The predecessor will be rightmost node
				predecessor = findMax(root.left)
			if root.right is not None:
				#The successor will be leftmost node
				successor = findMin(root.left)
			return predecessor,successor

		if root.key > key:
			predecessor = root
			predecessor,successor = self.findPreSucHelper(root.left,key,predecessor,successor)
			return predecessor,successor
		else:
			successor = root
			predecessor,successor = self.findPreSucHelper(root.right, key, predecessor,successor)
			return predecessor,successor