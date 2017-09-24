/*
Question: Pascal's Triangle II

Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?

*/


Best Solution:

public class Solution {
    public List<Integer> getRow(int rowIndex) {
       List<Integer> row = new ArrayList<Integer>();
       for(int i = 0 ; i <= rowIndex ; i++)
       {
           row.add(0,1);
           for(int k = 1; k < i ; k++)
                row.set(k , row.get(k+1) + row.get(k));
       }
       return row;
    }
}






Note:

1. this solution is very concise, easily handle the corner cases!!!
2....
3....