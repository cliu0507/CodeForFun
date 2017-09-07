Lowest Common Ancestor of Binary Tree


Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w 
as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

class TreeNode(object):
     def __init__(self, x):
         self.val = x
         self.left = None
         self.right = None


class Solution(object):
	