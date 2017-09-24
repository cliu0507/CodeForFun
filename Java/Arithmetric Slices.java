/*
Question: 413. Arithmetic Slices

A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequence:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
The following sequence is not arithmetic.

1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.

A slice (P, Q) of array A is called arithmetic if the sequence:
A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.

The function should return the number of arithmetic slices in the array A.


Example:

A = [1, 2, 3, 4]

return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.


*/

Dynamic Programming:

My Submission Solution: O(n) space + O(n) time 
public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int[] res = new int[A.length];
        int sum = 0;
        if(A.length < 3) return 0;
        res[0] = 0;
        res[1] = 0;
        int diff = A[1]-A[0];
        for(int i = 2 ; i < A.length ; i++)
        {
            int count=0;
            if(diff == A[i]-A[i-1])
            {
                count+=res[i-1];
                count+=1;
            }
            else
                count = 0;
            diff = A[i] - A[i-1];
            res[i] = count;
            sum += res[i];
        }
        return sum;
    }
}


Brute Force: O(n*n) Time and O(1) space


If want all arithmetric slices output by a list:
BackTracking:

public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        
        List<List<Integer>> fullList = new ArrayList<List<Integer>>();
        List<Integer> slice ;
        List<List<Integer>> tempList = new ArrayList<List<Integer>>(); 
        
        int[] res = new int[A.length];
        int sum = 0;
        if(A.length < 3) return 0;
        res[0] = 0;
        res[1] = 0;
        int diff = A[1]-A[0];
        for(int i = 2 ; i < A.length ; i++)
        {
            int count=0;
            if(diff == A[i]-A[i-1])
            {
                count+=res[i-1];
                List<List<Integer>> temp = new ArrayLsit<List<Integer>>();

                for(int j = 0 ; j < tempList.size() ; j++)
                {
                    slice = tempList.get(j);
                    slice.add(A[i]);
                    temp.add(slice);
                }
                tempList = temp;
                
                count+=1;
                slice = new ArrayList<Integer>();
                slice.add(A[i-2]);
                slice.add(A[i-1]);
                slice.add(A[i]);
                tempList.add(slice);
            }
            else
                tempList.clear();
                count = 0;
            diff = A[i] - A[i-1];
            res[i] = count;
            sum += res[i];
            fullList.addAll(tempList);
            
        }
        return sum;
    }
}




Note:

1....
2....
3....