Construct BT from Inorder and Postorder Traversal

class Solution(object):
    def buildTree(self, inorder, postorder):
        """
        :type preorder: List[int]
        :type inorder: List[int]
        :rtype: TreeNode
        """
        return self.buildTreeHelper(postorder, inorder,len(postorder)-1, 0, len(inorder)-1)
        
    def buildTreeHelper(self, postorder, inorder, postorderIndex, inorderIndexStart, inorderIndexEnd):
        if inorderIndexStart > inorderIndexEnd or postorderIndex < 0:
            return None
        else:
            rootVal = postorder[postorderIndex]
            pos = inorder.index(rootVal)
            root = TreeNode(rootVal)
            rightSubTreeLength = inorderIndexEnd - pos
            root.right = self.buildTreeHelper(postorder, inorder, postorderIndex-1, pos+1 , inorderIndexEnd)
            root.left = self.buildTreeHelper(postorder, inorder, postorderIndex-1-rightSubTreeLength, inorderIndexStart,pos-1)
            return root