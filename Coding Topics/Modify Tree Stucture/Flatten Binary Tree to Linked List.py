Flatten Binary Tree to Linked List

Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6


# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

这个是迭代的做法
O(n)时间O(n)space
如果左边子树非常大的话 其实worst case O(N*N)

class Solution(object):
    def flatten(self, root):
        """
        :type root: TreeNode
        :rtype: void Do not return anything, modify root in-place instead.
        """
        if root == None:
            return None
        else:
            self.flatten(root.left)
            self.flatten(root.right)
            leftNode = root.left
            rightNode = root.right
            if leftNode == None:
                return 
            else:
                root.left = None
                root.right = leftNode
                while(root.right!=None):
                    root = root.right
                root.right = rightNode
                return 

最好的做法是利用morris遍历 这个是递归的结果

Morris遍历的特点是寻找左边子树中可以沿着右边走最长的节点，并且利用这个节点做文章
这题是把right指针直接指向root的右边节点就可以了
如此反复 这样省去了最坏情况下重复寻找链表的尾巴的过程，