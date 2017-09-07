Verify Preorder Sequence in Binary Search Tree

给定一个 preorder sequence，只要这
个 sequence 可以生成一个 valid BST，都返回 true.

IDEA:
左右子树可以为空，但是不能违反 BST 子树性质。所以如果 > root 的连续数组后
面又出现了 < root 的元素，一定是 false.


class TreeNode(object):
	def __init__(self,x):
		self.left = None
		self.right = None
		self.key = x

class Solution(object):
	def verifyPreorder(self, preorder):
		#preorder type: []
		#rtype: Boolean
		start = 0
		end = len(preorder)-1
		return self.verifyPreorderHelper(preorder,start,end)

	def verifyPreorderHelper(self, preorder,start, end):
		if abs(start-end) <= 1:
			return True
		else:
			root = preorder[start]
			breakpoint = root[start+1]
			while(breakpoint<=end):
				if preorder[breakpoint] < root:
					breakpoint+=1
				else:
					break
			cur = breakpoint
			while(cur<=end):
				if preorder[breakpoint] < root:
					return False
				cur+=1
			return self.verifyPreorderHelper(preorder, start+1, breakpoint-1) && self.verifyPreorderHelper(preorder,breakpoint,end)
