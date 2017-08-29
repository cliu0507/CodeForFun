Sum of left leaves

Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.



class Solution(object):
    def sumOfLeftLeaves(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        if root == None:
            return 0
        elif root.left == None:
        	return sumOfLeftLeaves(root.right)
        elif root.left.left == None and root.left.right == None:
        	return root.left.value + sumOfLeftLeaves(root.right)
        else:
        	return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right)
            