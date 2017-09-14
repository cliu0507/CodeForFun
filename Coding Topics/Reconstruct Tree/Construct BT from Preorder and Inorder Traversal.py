Construct BT from Preorder and Inorder Traversal

# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def buildTree(self, preorder, inorder):
        """
        :type preorder: List[int]
        :type inorder: List[int]
        :rtype: TreeNode
        """
        return self.buildTreeHelper(preorder, inorder,0, 0, len(inorder)-1)
        
    def buildTreeHelper(self, preorder,inorder, preorderIndex, inorderIndexStart, inorderIndexEnd):
        if inorderIndexStart > inorderIndexEnd or preorderIndex >= len(preorder):
            return None
        else:
            rootVal = preorder[preorderIndex]
            pos = inorder.index(rootVal)
            root = TreeNode(rootVal)
            leftSubTreeLength = pos - inorderIndexStart
            root.left = self.buildTreeHelper(preorder, inorder, preorderIndex+1, inorderIndexStart, pos-1)
            root.right = self.buildTreeHelper(preorder, inorder, preorderIndex+1+leftSubTreeLength, pos+1 , inorderIndexEnd)
            return root
