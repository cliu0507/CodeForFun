
Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL


# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

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



class Solution(object):
    def reverseList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        return self.reverseHelper(head)
        
    
    def reverseHelper(self, head):
        if head != None:
            next = head.next
            if next != None:
                new_head = self.reverseHelper(next)
                next.next = head
                head.next = None
                return new_head
            else:
                return head 
        else:
            return None
            
        