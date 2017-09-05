Min/Max/Balanced Depth

Maximum Depth:recursion + Max
Minimum Depth:recursion + Min (注意如果在拐弯初缺了一个child并不代表是valid path)

Maximum Depth
Use Stack Iterative:

class TreeNode(object):
	def __init__(self,x):
		self.left = None
		self.right = None
		self.val = x

class Solution(object):
	def maxDepth(self,root):
		nodestack = []
		depthstack = []
		if not root:
			return 0
		else:
			nodestack.append(root)
			depthstack.append(1)
			maxDepth = 1
			while nodestack:
				cur = nodestack.pop()
				curDepth = depthstack.pop()
				maxDepth = max(maxDepth,curDepth)
				if cur.left:
					nodestack.append(cur.left)
					nodestack.append(curDepth+1)
				if cur.right:
					nodestack.append(cur.right)
					nodestack.append(curDepth+1)
			return maxDepth


Minimum Depth
import sys
class TreeNode(object):
	def __init__(self,x):
		self.left = None
		self.right = None
		self.val = x

class Solution(object):
    def minDepth(self,root):
        if root == None:
            return 0
        else:
            nodestack = []
            depthstack = []
            curDepth = 1
            minDepth = sys.maxsize
            cur = root
            while(cur!=None or len(nodestack)):
                while(cur!=None):
                    nodestack.append(cur)
                    depthstack.append(curDepth)
                    curDepth += 1
                    cur = cur.left
                cur = nodestack.pop()
                curDepth = depthstack.pop()
                if cur.left == None and cur.right == None:
                    minDepth = min(minDepth,curDepth)
                cur = cur.right
                curDepth += 1
            return minDepth



Balanced Binary Tree

Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree 
in which the depth of the two subtrees of every node never differ by more than 1.

Top-Down Approach (Brute Force, TLE on LeetCode)
class Solution(object):
    def isBalanced(self,root):
    	if not root:
    		return True
    	else:
    		return (True if abs(self.depth(root.right) - self.depth(root.left)) <= 1 
    			else False) & self.isBalanced(root.left) & self.isBalanced(root.right)

    def depth(self, root):
    	if root == None:
    		return 0
    	else:
    		return max(self.depth(root.left),self.depth(root.right)) +1
O(nlogn) Time 

改进：使用DFS的想法，这样每个DFS的函数既找到depth 也判断子树是否是balanced，这样的找depth就不用重复调用多次
其实DFS一般都是bottom-up的方法 从根节点网上回溯
Best Solution:
class Solution(object):
    def isBalanced(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        return self.dfsHeight(root)!=-1
        
    def dfsHeight(self,root):
        if root == None:
            return 0
        else:
            leftHeight = self.dfsHeight(root.left)
            rightHeight = self.dfsHeight(root.right)
            if leftHeight!=-1 and rightHeight!=-1:
                return max(leftHeight,rightHeight)+1 if abs(leftHeight - rightHeight) <= 1 else -1
            else:
                return -1