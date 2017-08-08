Merge Two Sorted Lists

'''
Question body
Merge two sorted linked lists and return it as a new list. 
The new list should be made by splicing together the nodes of the first two lists.
'''


'''
Idea:
Have two pointers
O(m+n)
'''

class Solution(object):
    def mergeTwoLists(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        dummyhead = ListNode(0)
        cur = dummyhead
        while (p!=None and q!=None):
            if p.val < q.val:
                cur.next = p
                p = p.next
                cur.next.next= None
                cur = cur.next
            else:
                cur.next = q
                q = q.next
                cur.next.next = None
                cur = cur.next
        #Neat
        cur.next = p or q
            return dummyhead.next
        

#A more neat solution without using extra p and q:
# iteratively
def mergeTwoLists1(self, l1, l2):
    dummy = cur = ListNode(0)
    while l1 and l2:
        if l1.val < l2.val:
            cur.next = l1
            l1 = l1.next
        else:
            cur.next = l2
            l2 = l2.next
        cur = cur.next
    cur.next = l1 or l2
    return dummy.next


def mergeTwoLists2(self, l1, l2):
    if not l1 or not l2:
        return l1 or l2
    if l1.val < l2.val:
        l1.next = self.mergeTwoLists(l1.next, l2)
        return l1
    else:
        l2.next = self.mergeTwoLists(l1, l2.next)
        return l2