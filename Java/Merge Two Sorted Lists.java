/*
21. Merge Two Sorted Lists
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

*/


My Solution:
Important:Could use a fake head to avoid edge case. Dummy head!!!
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null)
            return null;
        else
        {
            ListNode dummy = new ListNode(0);
            ListNode cur = dummy;
            ListNode p = l1;
            ListNode q = l2;
            while(p!=null && q!=null)
            {
                if(p.val < q.val)
                {
                    cur.next = p;
                    p = p.next;
                    cur = cur.next;
                }
                else
                {
                    cur.next = q;
                    q = q.next;
                    cur = cur.next;
                }
            }
            if(p!=null)
            {
                cur.next = p;
            }
            else
            {
                cur.next = q;
            }
            return dummy.next;
        }
        
    }
}



Solution 2, a recursive solution:
Note: This solution is not a tail-recursive, the stack will overflow while the list is too long :)
http://en.wikipedia.org/wiki/Tail_call    

    if(l1 == null) return l2;
    if(l2 == null) return l1;

    if(l1.val < l2.val) {
        l1.next = mergeTwoLists(l1.next, l2);
        return l1;
    } else {
        l2.next = mergeTwoLists(l2.next, l1);
        return l2;
    }