Binary Tree Paths

Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]


# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

Use Two stack, one to store node, one to store the path to current visting node(exclusive current node)
class Solution(object):
    def binaryTreePaths(self, root):
        """
        :type root: TreeNode
        :rtype: List[str]
        """
        result = []
        pathstack = []
        nodestack = []
        if not root:
            return []
        else:
            #pathstack store the path above that node(excluding the node)
            nodestack.append(root)
            pathstack.append("")
            while nodestack:
                node = nodestack.pop()
                string = pathstack.pop()
                if node.left == None and node.right == None:
                    result.append(string+str(node.val))
                if node.left:
                    nodestack.append(node.left)
                    pathstack.append(string+str(node.val)+"->")
                if node.right:
                    nodestack.append(node.right)
                    pathstack.append(string+str(node.val)+"->")  
            return result
                    
BFS queue:
class Solution(object):
    def binaryTreePaths(self, root):
        """
        :type root: TreeNode
        :rtype: List[str]
        """
        if not root:
            return []
        res, queue = [], collections.deque([(root, "")])
        while queue:
            node, ls = queue.popleft()
            if not node.left and not node.right:
                res.append(ls+str(node.val))
            if node.left:
                queue.append((node.left, ls+str(node.val)+"->"))
            if node.right:
                queue.append((node.right, ls+str(node.val)+"->"))
        return res



# dfs recursively
def binaryTreePaths(self, root):
    if not root:
        return []
    res = []
    self.dfs(root, "", res)
    return res

def dfs(self, root, ls, res):
    if not root.left and not root.right:
        res.append(ls+str(root.val))
    if root.left:
        self.dfs(root.left, ls+str(root.val)+"->", res)
    if root.right:
        self.dfs(root.right, ls+str(root.val)+"->", res)                    