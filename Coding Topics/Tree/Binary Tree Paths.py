Binary Tree Paths
Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]


dfs recursively:
class Solution(object):
    def binaryTreePaths(self, root):
        """
        :type root: TreeNode
        :rtype: List[str]
        """
        result = []
        path = ""
        if not root:
            return result
        else:
            path+=str(root.val)
            if not root.left and not root.right:
                result.append(path)
                return result
            self.binaryTreePathsHelper(root.left, path, result)
            self.binaryTreePathsHelper(root.right, path, result)
            return result
        
    def binaryTreePathsHelper(self,root, path,result):
        if root == None:
            return
        else:
            path += ("->"+str(root.val))
            if root.left == None and root.right == None:
                result.append(path)
            else:
                self.binaryTreePathsHelper(root.left, path, result)
                self.binaryTreePathsHelper(root.right, path, result)
            return

DFS recursively
def binaryTreePaths(self, root):
    if not root:
        return []
    return [str(root.val) + '->' + path
            for kid in (root.left, root.right) if kid
            for path in self.binaryTreePaths(kid)] or [str(root.val)] 


Use Two stack, one to store node, one to store the path to current visting node(exclusive current node)
其实就是模仿DFS 的栈的特性 很牛逼，所以所有的backtracking问题其实都可以这么做，用一个额外的一个栈模拟node栈的情况 里面存previous path或者result
class Solution(object):
    def binaryTreePaths(self, root):
        """
        :type root: TreeNode
        :rtype: List[str]
        """
        result = []
        pathstack = []
        nodestack = []
        if not root:
            return []
        else:
            #pathstack store the path above that node(excluding the node)
            nodestack.append(root)
            pathstack.append("")
            while nodestack:
                node = nodestack.pop()
                string = pathstack.pop()
                if node.left == None and node.right == None:
                    result.append(string+str(node.val))
                if node.left:
                    nodestack.append(node.left)
                    pathstack.append(string+str(node.val)+"->")
                if node.right:
                    nodestack.append(node.right)
                    pathstack.append(string+str(node.val)+"->")  
            return result
              

另外一种stack的写法（和inorder stack相似）
有一个pathstack存储path to current node(不包括当前node)
class Solution(object):
    def binaryTreePaths(self, root):
        """
        :type root: TreeNode
        :rtype: List[str]
        """
        result = []
        nodestack = []
        pathstack = []
        cur = root
        path = ""
        while(cur!=None or len(nodestack)!=0):
            while(cur!=None):
                nodestack.append(cur)
                pathstack.append(path)
                path = path + "->"+ str(cur.val)
                cur = cur.left
            cur = nodestack.pop()
            path = pathstack.pop()
            if cur.right == None and cur.left == None:
                result.append(path+"->"+str(cur.val))
            path = path + "->"+ str(cur.val)
            cur = cur.right 
        return [string[2:] for string in result]


BFS queue:
class Solution(object):
    def binaryTreePaths(self, root):
        """
        :type root: TreeNode
        :rtype: List[str]
        """
        if not root:
            return []
        res, queue = [], collections.deque([(root, "")])
        while queue:
            node, ls = queue.popleft()
            if not node.left and not node.right:
                res.append(ls+str(node.val))
            if node.left:
                queue.append((node.left, ls+str(node.val)+"->"))
            if node.right:
                queue.append((node.right, ls+str(node.val)+"->"))
        return res           