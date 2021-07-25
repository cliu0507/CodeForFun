class TreeNode:
    def __init__(self,value):
        self.val = value
        self.left = None
        self.right = None


class Solution:
    def preorderTraversal(self, root: TreeNode):
        res = []
        stack = [(root, False)]
        while stack:
            node, visited = stack.pop()  # the last element
            if node:
                if visited:  
                    res.append(node.val)
                else:  # preorder: root -> left -> right
                    stack.append((node.right, False))
                    stack.append((node.left, False))
                    stack.append((node, True))
        return res

    def inorderTraversal(self, root: TreeNode):
        res = []
        stack = [(root, False)]
        while stack:
            node, visited = stack.pop()  # the last element
            if node:
                if visited:
                    res.append(node.val)
                else:  # inorder: left -> root -> right
                    stack.append((node.right, False))
                    stack.append((node, True))
                    stack.append((node.left, False))
        return res

    def postorderTraversal(self, root: TreeNode):
        res = []
        stack = [(root, False)]
        while stack:
            node, visited = stack.pop()  # the last element
            if node:
                if visited:
                    res.append(node.val)
                else:  # postorder: left -> right -> root
                    stack.append((node, True))
                    stack.append((node.right, False))
                    stack.append((node.left, False))
        return res

    # another way of iterative inorder
    def inorderTraversal2(self, root):
        res, stack = [], []
        while root or stack:
            while root:
                stack.append(root)
                root = root.left
            root = stack.pop()
            res.append(root.val)
            root = root.right
        return res

    # another way of iterative preorder
    def preorderTraversal2(self, root):
        res, stack = [], []
        while root or stack:
            while root:
                stack.append(root)
                res.append(root.val)
                root = root.left
            root = stack.pop()
            root = root.right
        return res

    # another way of iterative postorder

    def postorderTraversa2(self, root):
        stack, res = [], []
        prev = None
        while stack or root:
            if root:
                stack.append(root)
                root = root.left
            elif stack[-1].right and stack[-1].right != prev:
                root = stack[-1].right
            else: # either right child has been processed or no right child
                prev = stack.pop()
                res.append(prev.val)
        return res