/*
Question: 24. Swap Nodes in Pairs
Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.

*/


Solution 1: Recusive Way
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null)
            return null;
        else if(head.next == null)
            return head;
        else
        {
            ListNode temp = head.next;
            head.next = head.next.next;
            temp.next = head;
            head.next = swapPairs(head.next);
            return temp;
        }
    }
}

Solution 2: Iterative Way
Need to care of head node.

public class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode cur = head;
        ListNode previous = cur;
        
        if(cur!=null && cur.next!=null)
        {
            ListNode temp = cur.next;
            cur.next = cur.next.next;
            temp.next = cur;
            cur = cur.next;
            previous = temp.next;
            head = temp;
        }
        
        while(cur!=null && cur.next!=null)
        {
            
            ListNode temp = cur.next;
            cur.next = cur.next.next;
            temp.next = cur;
            cur = cur.next;
            previous.next = temp;
            previous = temp.next;
            
        }
        return head;
    }
}

Solution3: Use dummy Nodes
public ListNode swapPairs(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode current = dummy;
    while (current.next != null && current.next.next != null) {
        ListNode first = current.next;
        ListNode second = current.next.next;
        first.next = second.next;
        current.next = second;
        current.next.next = first;
        current = current.next.next;
    }
    return dummy.next;
}



Note:

1....
2....
3....