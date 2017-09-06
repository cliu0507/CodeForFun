BST Search and Insertion

Binary Search Tree, is a node-based binary tree data structure which has the following properties:

The left subtree of a node contains only nodes with keys less than the node’s key.
The right subtree of a node contains only nodes with keys greater than the node’s key.
The left and right subtree each must also be a binary search tree.
There must be no duplicate nodes.


Searching a key
To search a given key in Bianry Search Tree, we first compare it with root, 
if the key is present at root, we return root. If key is greater than root’s key, 
we recur for right subtree of root node. Otherwise we recur for left subtree.


# A utility function to search a given key in BST
def search(root,key):
     
    # Base Cases: root is null or key is present at root
    if root is None or root.val == key:
        return root
 
    # Key is greater than root's key
    if root.val < key:
        return search(root.right,key)
   
    # Key is smaller than root's key
    return search(root.left,key)


Insertion of a key(注意插入的时候其实是先搜索 一旦到leaf node再进行插入)
A new key is always inserted at leaf. 
We start searching a key from root till we hit a leaf node. 
Once a leaf node is found, the new node is added as a child of the leaf node.

class Node:
    def __init__(self,key):
        self.left = None
        self.right = None
        self.val = key
 
# A utility function to insert a new node with the given key
def insert(root,node):
    if root is None:
        root = node
    else:
        if root.val < node.val:
            if root.right is None:
                root.right = node
            else:
                insert(root.right, node)
        else:
            if root.left is None:
                root.left = node
            else:
                insert(root.left, node)
 
# A utility function to do inorder tree traversal
def inorder(root):
    if root:
        inorder(root.left)
        print(root.val)
        inorder(root.right)