Given a binary tree where all the right nodes are either leaf nodes with a sibling 
(a left node that shares the same parent node) or empty, 
flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
     1
    / \
   2   3
 / \
4  5

return the root of the binary tree [4,5,2,#,#,3,1].
  4 
/ \
5  2
  / \
 3   1


参考reverse linkedlist,只是维度多了一维
class Solution(object):
    def reverseList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        prev = None
        while(head!=None):
            temp = head.next
            head.next = prev
            prev = head
            head = temp
        return prev