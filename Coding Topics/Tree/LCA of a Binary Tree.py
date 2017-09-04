LCA of a Binary Tree

Lowest Common Ancestor
class TreeNode(object):
     def __init__(self, x):
         self.val = x
         self.left = None
         self.right = None

1. Brute Force: 每个节点node作为子树 测试node是否contain p和q, 每次测试便利整个子树
O(nlogn) - 因为每个节点被遍历的次数和height有关 越低则遍历越多次。太多次重复调用

2. 改进：想法上，分别判断坐 node.left是否contain p, 是否contain q
	if contains(node.left, p) == true == contains(node.left, q),那么LCA一定在左边
	if contains(node.right, p) == true == contains(node.right, q) 那么LCA一定在右边
	if 一左一右 怎么LCA = root


	class Solution(object):
		def LCA(self, root, p , q):
			if root == None:
				return None:
			elif root == p:
				return root
			elif root == q:
				return root:
			else:
				if contains(root.left,p) == True and contains(root.left,q) == True:
					return LCA(root.left, p , q)
				elif contains(root.right,p) == True and contains(root.right,q) == True:
					return LCA(root.right, p, q)
				else:
					return root

		def contains(self, root, m):
			if root == None:
				return None
			elif root == m:
				return True
			else:
				return self.contains(root.left, m) || self.contains(root.right, m)
	O(n)?
TLE in leetcode


其实根据子树的递归特性 并不需要contain这个函数


BEST Solution:
对于给定Node为root的tree中是否包含p或者q，只要包含一个 就不返回null
class Solution(object):

    def lowestCommonAncestor(self, root, p, q):
        if root in (None, p, q): return root
        left, right = (self.lowestCommonAncestor(kid, p, q)
                    for kid in (root.left, root.right))
        return root if left and right else left or right



Another method:(use path)
问题是需要存储
Method 1 (By Storing root to n1 and root to n2 paths):
Following is simple O(n) algorithm to find LCA of n1 and n2.
1) Find path from root to n1 and store it in a vector or array.
2) Find path from root to n2 and store it in another vector or array.
3) Traverse both paths till the values in arrays are same. Return the common element just before the mismatch.


# O(n) solution to find LCS of two given values n1 and n2
 
# A binary tree node
class Node:
    # Constructor to create a new binary node
    def __init__(self, key):
        self.key =  key
        self.left = None
        self.right = None
 
# Finds the path from root node to given root of the tree.
# Stores the path in a list path[], returns true if path 
# exists otherwise false
def findPath( root, path, k):
 
    # Baes Case
    if root is None:
        return False
 
    # Store this node is path vector. The node will be
    # removed if not in path from root to k
    path.append(root.key)
 
    # See if the k is same as root's key
    if root.key == k :
        return True
 
    # Check if k is found in left or right sub-tree
    if ((root.left != None and findPath(root.left, path, k)) or
            (root.right!= None and findPath(root.right, path, k))):
        return True
 
    # If not present in subtree rooted with root, remove
    # root from path and return False
      
    path.pop()
    return False
 
# Returns LCA if node n1 , n2 are present in the given
# binary tre otherwise return -1
def findLCA(root, n1, n2):
 
    # To store paths to n1 and n2 fromthe root
    path1 = []
    path2 = []
 
    # Find paths from root to n1 and root to n2.
    # If either n1 or n2 is not present , return -1 
    if (not findPath(root, path1, n1) or not findPath(root, path2, n2)):
        return -1
 
    # Compare the paths to get the first different value
    i = 0
    while(i < len(path1) and i < len(path2)):
        if path1[i] != path2[i]:
            break
        i += 1
    return path1[i-1]
 
 
# Driver program to test above function
# Let's create the Binary Tree shown in above diagram
root = Node(1)
root.left = Node(2)
root.right = Node(3)
root.left.left = Node(4)
root.left.right = Node(5)
root.right.left = Node(6)
root.right.right = Node(7)
 
print "LCA(4, 5) = %d" %(findLCA(root, 4, 5,))
print "LCA(4, 6) = %d" %(findLCA(root, 4, 6))
print "LCA(3, 4) = %d" %(findLCA(root,3,4))
print "LCA(2, 4) = %d" %(findLCA(root,2, 4))
 
# This code is contributed by Nikhil Kumar Singh(nickzuck_007)