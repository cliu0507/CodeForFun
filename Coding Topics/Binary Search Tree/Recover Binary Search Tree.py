Recover Binary Search Tree

LeetCode No.99
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?


如果通过inorder找到两个交换的树
从左往右找递增序列里面第一个变小的，prev 为 swapped;
从右往左找递减序列里面第一个变大的，prev 为 swapped.


# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

Recursive Version => Note: it is not O(1) space but O(n)
import sys
class Solution(object):
	def recoverTree(self, root):
		"""
		:type root: TreeNode
		:rtype: void Do not return anything, modify root in-place instead.
		"""
		if root == None:
			return root
		p = None
		q = None
		prev = TreeNode(-100000)
		#Found p,q
		
		p,q,prev = self.traverse(root,p,q,prev)

		#Swap items
		temp = p.val;
		p.val = q.val;
		q.val = temp;
		return

	def traverse(self,root, p, q, prev):
		if root == None:
			return p,q,prev
		else:
			#这个地方比较tricky
			p,q,prev = self.traverse(root.left,p,q,prev)
			#找到第一个被交换的p
			if p == None and prev.val > root.val:
				p = prev
			#再找到第一个被交换的p的条件下 开始寻找q，一旦又右开始变小，表示这就是q
			if p != None and prev.val > root.val:
				q = root
			prev = root
			p,q,prev = self.traverse(root.right,p,q,prev)
			return p,q,prev


还有一种做法比较好理解

就是两次inorder traversal，一次从左向右 一次从右向左，不过注意每次都要early termination，不然太过耗时
也可以用stack inorder traversal来做 一样的



最后注意 其实这个方法并不是O(1)的空间 递归其实是用了栈

最好的方法是morris traversal
见
https://discuss.leetcode.com/topic/9305/detail-explain-about-how-morris-traversal-finds-two-incorrect-pointer
