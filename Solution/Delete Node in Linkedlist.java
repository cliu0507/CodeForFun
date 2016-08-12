/*
Question: Delete Node in a Linked List

Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list should become 1 -> 2 -> 4 after calling your function.

*/





Solution 1:
/*
We can't really delete the node, but we can kinda achieve the same effect by instead removing the next node after copying its data into the node that we were asked to delete.

*/

public class Solution {
    public void deleteNode(ListNode node) {
        if(node==null)
            return;
        else
        {
            if(node.next==null)
            {
                node=null;
                return;
            }
            else
            {
                node.val=node.next.val;
                node.next = node.next.next;
                return;
            }
        }
    }
}






Note:

1....
2....
3....