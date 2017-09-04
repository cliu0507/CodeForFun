Binary Tree Level Order Traversal

Given a binary tree, return the level order traversal of its nodes values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7

BFS Iteratively
class Solution(object):
    def levelOrder(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """
        result = []
        if root == None:
            return result
        else:
            queue = []
            queue.append(root)
            nodecount = 1
            curlayer = []
            while queue:
                if nodecount == 0:
                    result.append(curlayer)
                    curlayer = []
                    nodecount = len(queue)
                node = queue.pop()
                if node.left:
                    queue.insert(0,node.left)
                if node.right:
                    queue.insert(0,node.right)
                curlayer.append(node.val)
                nodecount -= 1
            if curlayer:
                result.append(curlayer)
            return result   



Follow Up: (Return bottom-up level order traversal)
Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]