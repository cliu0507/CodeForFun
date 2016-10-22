/*
Pascal's Triangle

Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/

My solution
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> row = new ArrayList<Integer>();
        if(numRows==0)
            return result;
        row.add(1);
        result.add(row);
        if(numRows==1)
            return result;
        else
        {
            for(int k = 2 ; k <= numRows ;k++ )
            {   
                List<Integer> nextrow = new ArrayList<Integer>();
                nextrow.add(1);
                for(int i = 1 ; i < k-1 ; i++)
                {
                    nextrow.add((int)row.get(i-1)+(int)row.get(i));
                }
                nextrow.add(1);
                result.add(nextrow);
                row = nextrow;
            }
        }
        return result;
    }
}


Best Solution(more concise):

Note: Only use one temporary array *****

Also we could operate from tail to header

public class Solution {
public List<List<Integer>> generate(int numRows)
{
    List<List<Integer>> allrows = new ArrayList<List<Integer>>();
    ArrayList<Integer> row = new ArrayList<Integer>();
    for(int i=0;i<numRows;i++)
    {
        row.add(0, 1);
        for(int j=1;j<row.size()-1;j++)
            row.set(j, row.get(j)+row.get(j+1));
        allrows.add(new ArrayList<Integer>(row));
    }
    return allrows;
    
}
