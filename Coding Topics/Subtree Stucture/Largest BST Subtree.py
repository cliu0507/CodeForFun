Largest BST Subtree

Given a Binary Tree, write a function that returns the size of the largest subtree which is also a Binary Search Tree (BST). If the complete Binary Tree is BST, then return the size of whole tree.

Input: 
      5
    /  \
   2    4
 /  \
1    3

Output: 3 
The following subtree is the 
maximum size BST subtree 
   2  
 /  \
1    3


Input: 
       50
     /    \
  30       60
 /  \     /  \ 
5   20   45    70
              /  \
            65    80
Output: 5
The following subtree is the
maximum size BST subtree 
      60
     /  \ 
   45    70
        /  \
      65    80



分析：
第一个方法就是递归

写成bottom-up的样式 只遍历所以点一遍，所以需要helperfunction返回多个子问题的结果
1. Whether the subtree itself is BST or not 
2. If the subtree is left subtree of its parent, 
then maximum value in it. And if it is right subtree then minimum value in it.
3. Size of this subtree if this subtree is BST (In the following code, return value of largestBSTtil() is used for this purpose)
返回tuple就可以啦！如果使用python的话 ，使用java的话 需要定义value class
