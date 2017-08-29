Tree Traversal

Return List to store treenode
(Preorder, Inorder, PostOrder)

class TreeNode(object):
     def __init__(self, x):
         self.val = x
         self.left = None
         self.right = None


Preorder Recursive

class Solution(object):
    def preorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        result = []
        return self.preorderTraversalHelper(root,result)

    def preorderTraversalHelper(self, root,result):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if root:
        	result.append(root.val)
			self.preorderTraversalHelper(root.left,result)
			self.preorderTraversalHelper(root.right,result)


Preorder Iterative(Stack)
class Solution(object):
    def preorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        result = []
        stack = []
        if not root:
        	return result
        else:
        	stack.append(root)
        	while stack:
        		node = stack.pop()
        		#Preorder
        		result.append(root.val)
        		if node.left:
        			stack.append(node.left)
        		if node.right:
        			stack.append(node.right)


Preorder Iterative(Parent)

What if tree has parent pointer?



class TreeNode(object):
     def __init__(self, x):
         self.val = x
         self.left = None
         self.right = None
         self.parent = None


1. cur 不停的像左下或者右下找
2. 一旦到了叶子节点就开始回溯
3. 2种情况 1. 左叶子的话 就一直回溯到最近的一个parent.right!=none的节点 2. 右叶子的话 就需要回溯到最近一个parent.right!=none的节点
注意不可以是本身的这个节点，所以有 cur.parent.right == cur的while判断，如果相等说明应该接着往上回溯（换句话说应该是知道回溯到走向变成从左往上为止）
见github algorithm note.pdf page 183

class Solution(object):
    def preorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        cur = root
        while cur:
        	result.append(cur)
        	if cur.left:
        		cur = cur.left
        	elif cur.right:
        		cur = cur.right
        	else:
        		#Start to traceback
        		#不停的回溯
        		while cur.parent != None and (cur.parent.right == None or cur.parent.right == cur):
        			cur = cur.parent
        		if cur.parent == None:
        			break
        		#找到了cur，把cur赋值为同右节点
        		cur = cur.parent.right

        return result




Inorder Recursive:
class Solution(object):
    def inorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        result = []
        return self.inorderTraversalHelper(root,result)

    def inorderTraversalHelper(self, root,result):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if root:
        	self.inorderTraversalHelper(root.left,result)
        	result.append(root.val)
			self.inorderTraversalHelper(root.right,result)

Inorder Iterative:
class Solution(object):
    def inorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        result = []
        stack = []
        if not root:
        	return result
        else:
        	stack.append(root)
        	while stack:
        		cur = stack.pop()
        		if cur.left:
        			stack.append(cur.left)
        		if cur.right:
        			stack.append(cur.right)
