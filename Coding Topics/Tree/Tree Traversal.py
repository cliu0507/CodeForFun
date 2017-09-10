Tree Traversal

Return List to store treenode
(Preorder, Inorder, PostOrder)

preorder 直接用 stack; （栈存还未visit到的左边右边节点）               (note:其实也可以用 stack + cur)
inorder 用 stack + cur;（栈存左边节点）
postorder 用 stack + cur + prev; (需要操作栈顶元素， 栈存当前节点cur和cur.right)



class TreeNode(object):
     def __init__(self, x):
         self.val = x
         self.left = None
         self.right = None


Preorder Recursive

class Solution(object):
    def preorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        result = []
        return self.preorderTraversalHelper(root,result)

    def preorderTraversalHelper(self, root,result):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if root:
        	result.append(root.val)
			self.preorderTraversalHelper(root.left,result)
			self.preorderTraversalHelper(root.right,result)


Preorder Iterative(Stack only) 
class Solution(object):
    def preorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        result = []
        stack = []
        if not root:
        	return result
        else:
        	stack.append(root)
        	while stack:
        		node = stack.pop()
        		#Preorder
        		result.append(root.val)
        		if node.right:
        			stack.append(node.right)
        		if node.left:
        			stack.append(node.left)


Preorder Iterative(Stack + cur) 
class Solution(object):
    def preorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        result = []
        stack = []
        
        cur = root
        while(cur!=None and len(stack) != 0):
            while cur:
                result.append(cur)
                stack.append(cur)
                cur = cur.left
            cur = stack.pop()
            cur = cur.right



Preorder Iterative(Parent)

What if tree has parent pointer?



class TreeNode(object):
     def __init__(self, x):
         self.val = x
         self.left = None
         self.right = None
         self.parent = None


1. cur 不停的像左下或者右下找
2. 一旦到了叶子节点就开始回溯
3. 2种情况 1. 左叶子的话 就一直回溯到最近的一个parent.right!=none的节点 2. 右叶子的话 就需要回溯到最近一个parent.right!=none的节点
注意不可以是本身的这个节点，所以有 cur.parent.right == cur的while判断，如果相等说明应该接着往上回溯（换句话说应该是知道回溯到走向变成从左往上为止）
见github algorithm note.pdf page 183

class Solution(object):
    def preorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        cur = root
        while cur:
        	result.append(cur)
        	if cur.left:
        		cur = cur.left
        	elif cur.right:
        		cur = cur.right
        	else:
        		#Start to traceback
        		#不停的回溯
        		while cur.parent != None and (cur.parent.right == None or cur.parent.right == cur):
        			cur = cur.parent
        		if cur.parent == None:
        			break
        		#找到了cur，把cur赋值为同右节点
        		cur = cur.parent.right

        return result




Inorder Recursive:
class Solution(object):
    def inorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        result = []
        return self.inorderTraversalHelper(root,result)

    def inorderTraversalHelper(self, root,result):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if root:
        	self.inorderTraversalHelper(root.left,result)
        	result.append(root.val)
			self.inorderTraversalHelper(root.right,result)

Inorder Iterative: (两个while)
Inorder和preorder的stack存的是不一样的
inorderTraversal的stack存储所有一层一层左边节点 可以作为inorder回溯的parent节点,（由于需要inorder遍历 不可以丢失parent left和right的信息）
preorderTraversal的stack存储的就是所有节点类似于DFS,parent的right left信息不再需要，因为parent已经preorder visit过了

class Solution(object): (Stack + cur)
    def inorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        result = []
        stack = []
        cur = root
        while(cur!=None || len(stack)!=0):
            while(cur!=None):
                stack.append(cur)
                cur=cur.left
            cur = stack.pop()
            result.append(cur.val)
            cur = cur.right


Inorder Iterative(Parent)

What if tree has parent pointer?

class TreeNode(object):
     def __init__(self, x):
         self.val = x
         self.left = None
         self.right = None
         self.parent = None

1.首先找到leftmost node
2.然后print 这个leftmost node
3.开始回溯 向右上方向回溯（print 每个回溯的节点，这就是inorder和preorder的一个不同） 找到第一个右节点非空的node的时候停止， print这个node, cur=node
4.注意有一种情况是从cur开始回溯的时候 上一个节点的右节点就是cur 那么不认为找到，必须接着回溯（因为本节点作为右节点其实已经visit过了）
5.找到了满足停止条件的parent节点，此时print parent 然后设置 cur=parent.right（转到右边了）

原理上不停的找下一个节点
class Solution(object):
    def inorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        result = []
        cur = root
        while cur.left:
            cur = cur.left
        #Now cur is the leftmost node
        while cur:
            result.append(cur.val)
            cur = self.findSuccessor(cur, result)

    def findSuccessor(cur,result):
        #cur has right node
        if cur.right!=None:
            cur = cur.right
            while cur.left:
                cur = cur.left
        else:
            while (cur.parent!=None and cur.parent.right == cur):
                cur = cur.parent
            if cur.parent == None:
                return None
            cur = cur.parent
        return cur



Iterative Postorder Traversal | Set 1 (Using One Stacks)

The postorder traversal can easily be done using two stacks though. The idea is to push reverse postorder traversal to a stack. Once we have reverse postorder traversal in a stack, we can just pop all items one by one from the stack and print them, this order of printing will be in postorder because of LIFO property of stacks. Now the question is, how to get reverse post order elements in a stack – the other stack is used for this purpose. For example, in the following tree, we need to get 1, 3, 7, 6, 2, 5, 4 in a stack. If take a closer look at this sequence, we can observe that this sequence is very similar to preorder traversal. The only difference is right child is visited before left child and therefore sequence is “root right left” instead of “root left right”. So we can do something like iterative preorder traversal with following differences.
a) Instead of printing an item, we push it to a stack.
b) We push left subtree before right subtree.

1.1 Create an empty stack
2.1 Do following while root is not NULL
    a) Push root's right child and then root to stack.
    b) Set root as root's left child.
2.2 Pop an item from stack and set it as root.
    a) If the popped item has a right child and the right child 
       is at top of stack, then remove the right child from stack,
       push the root back and set root as root's right child.
    b) Else print root's data and set root as NULL.
2.3 Repeat steps 2.1 and 2.2 while stack is not empty.                



主要的idea：
http://www.geeksforgeeks.org/iterative-postorder-traversal-using-stack/

不停向左下探索
每次只进入栈 两种元素 一种是当前节点q 一种是当前节点的右子节点q.right（如果有）
出栈的时候会出q, 这个时候需要检测是否q.right以前被pop过，如果被visit过那么这个右边节点不应该在栈中了
如果q.right没有被print过，那么已经pop出的q 需要被重新放回栈, q.right则需要从栈中移除。同时设置（cur=q.right）说明开始遍历q.right的子树，
等到遍历print完q.right的子树 最后才可以真正的把q pop出来 （左子树完成， 右子树完成 才是根节点）

class Solution(object):
    
    def peek(self,stack):
        if len(stack) > 0 :
            return stack[-1]
        return None
    
    def postorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        stack = []
        result = []
        cur = root
        if cur == None:
            return result
        
        
        while(cur != None):
            if cur.right:
                stack.append(cur.right)
            stack.append(cur)
            
            cur = cur.left
        #cur is the leftmost node
        
        while(len(stack)!=0):
            cur = stack.pop()
            #cur doesn't have right node
            #如果stack顶部的元素和cur一样，说明cur的右边节点还没有print完成，此时不可以pop cur,所以需要放回cur到stack顶部，把cur设置为右边subtree的parent node
            if cur.right is not None and (self.peek(stack) == cur.right):
                stack.pop()
                stack.append(cur) #Push it back
                cur = cur.right
                
                #find leftmost node of right subtree
                while cur:
                    if cur.right:
                        stack.append(cur.right)
                    stack.append(cur)
                    cur = cur.left
                
            else:
                result.append(cur.val)
                cur = None
        return result        
        

Iterative Postorder Traversal | Method 2

Reverse the process of preorder, note this "preorder has to be firstly right then left"



还有一种思路：
要保证根结点在左孩子和右孩子访问之后才能访问，因此对于任一结点P，先将其入栈。
如果P不存在左孩子和右孩子，则可以直接访问它；
或者P存在左孩子或者右孩子，但是其左孩子和右孩子都已被访问过了，则同样可以直接访问该结点。
若非上述两种情况，则将P的右孩子和左孩子依次入栈，这样就保证了每次取栈顶元素的时候，左孩子在右孩子前面被访问，左孩子和右孩子都在根结点前面被访问。
        