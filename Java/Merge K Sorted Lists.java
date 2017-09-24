/*
23. Merge k Sorted Lists

Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

*/

IMPORTANT NOTE: There are two methods to solve the problem : One is divide and conquer, another one is Prority Queue!!!


1.Divide and Conquer Solution: Recursive Way

Time complexity = O(k*n*logk)

public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0)
            return null;
        
        return merge(lists, 0 , lists.length-1);
    }
    
    
    public ListNode merge(ListNode[] lists, int start, int end)
    {
        if(end == start)
            return lists[start];
        else if(end-start == 1)
        {
            return mergeTwoLists(lists[end] , lists[start]);
        }
        else
        {
            int middle = (end+start)/2;
            return mergeTwoLists(merge(lists, start , middle) , merge(lists, middle+1 , end));
        }
    }
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2)
    {
            if(l1 == null) return l2;
            if(l2 == null) return l1;

            if(l1.val < l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            } 
            else 
            {
                l2.next = mergeTwoLists(l2.next, l1);
                return l2;
            }
    }
}


Another Solution: Slightly difference in recursion
public ListNode mergeKLists(List<ListNode> lists) {
        int length = lists.size() ;

        if(length == 0)
            return null ;
        if(length == 1){
            return lists.get(0) ;
        }

        int mid = (length - 1)/2 ;
        ListNode l1 = mergeKLists(lists.subList(0,mid + 1)) ;
        ListNode l2 = mergeKLists(lists.subList(mid + 1,length)) ;

        return mergeTowLists(l1,l2) ;

    }




Another Good Recursion => Update lists in each merge: // Nice

public ListNode merge(ListNode[] lists)
    {
        while(lists.length!=1)
        {
            ListNode[] temp;
            if(lists.length%2 == 0)
            {
                temp = new ListNode[lists.length/2];
                for(int i = 0 ; i < lists.length/2 ; i++)
                {
                    ListNode newlist = mergeTwoLists(lists[i], lists[lists.length-1-i]);
                    temp[i] = newlist;
                }
            }
            else
            {
                temp = new ListNode[lists.length/2+1];
                for(int i = 0 ; i < lists.length/2 ; i++)
                {
                    ListNode newlist = mergeTwoLists(lists[i], lists[lists.length-1-i]);
                    temp[i] = newlist;
                }
                temp[temp.length-1] = lists[lists.length/2];
                
            }
            lists = temp;
        }
        return lists[0];
    }


2. Prority Queue Method
