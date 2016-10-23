/*
Question:
You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8

*/

Note: reverse order, need to consider carry

Solution 1:
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while(l1 != null || l2 != null || carry !=0)
        {
            if(l1 == null && l2 != null)
            {
                cur.next = new ListNode(l2.val + carry);
                l2 = l2.next;
            }
            else if (l2 == null && l1!=null)
            {
                cur.next = new ListNode(l1.val + carry);
                l1 = l1.next;
            }
            else if(l1 != null && l2 != null)
            {
                cur.next = new ListNode(l1.val + l2.val + carry);
                l1 = l1.next;
                l2 = l2.next;
            }
            else if(carry!=0)
            {
                cur.next = new ListNode(carry);
                break;
            }
            else
            {
                break;
            }
            carry = 0;
            cur = cur.next;
            if(cur.val >= 10)
            {
                cur.val = (cur.val)%10;
                carry = 1;
            }
        }
        return dummy.next;
    }
}

Best Solution:
Solution 2:
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ln1 = l1, ln2 = l2, head = null, node = null;
        int carry = 0, remainder = 0, sum = 0;
        head = node = new ListNode(0);
        
        while(ln1 != null || ln2 != null || carry != 0) {
            sum = (ln1 != null ? ln1.val : 0) + (ln2 != null ? ln2.val : 0) + carry;
            carry = sum / 10;
            remainder = sum % 10;
            node = node.next = new ListNode(remainder);
            ln1 = (ln1 != null ? ln1.next : null);
            ln2 = (ln2 != null ? ln2.next : null);
        }
        return head.next;
    }
}





Note:

1....
2....
3....