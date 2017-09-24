/*
Question: Triangle

Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

*/


Solution 1:

Dynamic programming: add each row value from top to bottom. For the middle elments, use min/max function

==>Use only ONE row of temporary list!!! ==> maxline

public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size() == 0)
            return 0;
        List<Integer> maxline = new ArrayList<Integer>();
        maxline.add(triangle.get(0).get(0));
        for(int i = 1 ; i < triangle.size() ; i++)
        {
            maxline.add(0,1);
            maxline.set(0, maxline.get(1) + triangle.get(i).get(0));
            for(int k = 1 ; k < i ; k++)
            {
                maxline.set(k , Math.min(maxline.get(k), maxline.get(k+1)) + triangle.get(i).get(k));
            }
            maxline.set(i , maxline.get(i) + triangle.get(i).get(i));
        }
        
        Iterator<Integer> iter = maxline.iterator();
        int result = Integer.MAX_VALUE;
        while(iter.hasNext())
        {
            result = Math.min(result, iter.next());
        }
        return result;
    }
}


Solution 2 : Best solution: Neat Solution:

 
From Bottom to TOP!!!!!!!! Very good!!!

public int minimumTotal(List<List<Integer>> triangle) {
    int[] A = new int[triangle.size()+1];
    for(int i=triangle.size()-1;i>=0;i--){
        for(int j=0;j<triangle.get(i).size();j++){
            A[j] = Math.min(A[j],A[j+1])+triangle.get(i).get(j);
        }
    }
    return A[0];
}


