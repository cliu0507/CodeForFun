 Count Complete Tree Nodes

 Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, 
is completely filled, and all nodes in the last level are as far left as possible. 
It can have between 1 and 2h nodes inclusive at the last level h.


这道题给定了一棵完全二叉树，让我们求其节点的个数。很多人分不清完全二叉树和满二叉树的区别，下面让我们来看看维基百科上对二者的定义：

完全二叉树 (Complete Binary Tree)：

A Complete Binary Tree （CBT) is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible.

对于一颗二叉树，假设其深度为d（d>1）。除了第d层外，其它各层的节点数目均已达最大值，且第d层所有节点从左向右连续地紧密排列，这样的二叉树被称为完全二叉树；

换句话说，完全二叉树从根结点到倒数第二层满足完美二叉树，最后一层可以不完全填充，其叶子结点都靠左对齐。

完美二叉树 (Perfect Binary Tree)：

A Perfect Binary Tree(PBT) is a tree with all leaf nodes at the same depth. All internal nodes have degree 2.

二叉树的第i层至多拥有个节点数；深度为k的二叉树至多总共有个节点数，而总计拥有节点数匹配的，称为“满二叉树”；



1.方法1:

通过上面的定义，我们可以看出二者的关系是，完美二叉树一定是完全二叉树，而完全二叉树不一定是完美二叉树。
那么这道题给的完全二叉树就有可能是完美二叉树，若是完美二叉树，节点个数很好求，为2的h次方-1，h为该完美二叉树的高度。
这道题可以用递归和非递归两种方法来解。
我们先来看递归的方法，思路是

分别找出以当前节点为根节点的左子树和右子树的高度并对比，如果相等，则说明是满二叉树，直接返回节点个数，
如果不相等，则节点个数为左子树的节点个数加上右子树的节点个数再加1(根节点)，其中左右子树节点个数的计算可以使用递归来计算

C++ Code:
class Solution {
public:
    int countNodes(TreeNode* root) {
        int hLeft = 0, hRight = 0;
        TreeNode *pLeft = root, *pRight = root;
        while (pLeft) {
            ++hLeft;
            pLeft = pLeft->left;
        }
        while (pRight) {
            ++hRight;
            pRight = pRight->right;
        }
        if (hLeft == hRight) return pow(2, hLeft) - 1;
        return countNodes(root->left) + countNodes(root->right) + 1;
    }
};


2. 方法2 感觉是更smart的方法
Explanation

The height of a tree can be found by just going left. Let a single node tree have height 0. 
Find the height h of the whole tree. If the whole tree is empty, i.e., has height -1, there are 0 nodes.

Otherwise check whether the height of the right subtree is just one less than that of the whole tree, 
meaning left and right subtree have the same height.

*If yes, then the last node on the last tree row is in the right subtree and the left subtree is a full tree of height h-1. 
So we take the 2^h-1 nodes of the left subtree plus the 1 root node plus recursively the number of nodes in the right subtree.
*If no, then the last node on the last tree row is in the left subtree and the right subtree is a full tree of height h-2. 
So we take the 2^(h-1)-1 nodes of the right subtree plus the 1 root node plus recursively the number of nodes in the left subtree.
Since I halve the tree in every recursive step, I have O(log(n)) steps. Finding a height costs O(log(n)). So overall O(log(n)^2).

首先cur = root, 一直走下来找到leftmost node,从而知道height
然后cur = cur.right, 把这个node作为root， 再算height，如果height比上一个height小1 那么说明...

https://discuss.leetcode.com/topic/15533/concise-java-solutions-o-log-n-2

# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def countNodes(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        if root == None:
            return 0
        else:
            countNode = 0
            height = self.checkHeight(root)
            while(root!=None):
                rightSubHeight = self.checkHeight(root.right)
                if rightSubHeight == height-1:
                    countNode += 2 ** (height-1) 
                    root = root.right
                else:
                    countNode += 2 ** (height-1-1)
                    root = root.left
                height-=1
            return countNode
                    
              
                
    def checkHeight(self,root):
        height = 0
        while(root != None):
            root = root.left
            height += 1
        return height
        
        