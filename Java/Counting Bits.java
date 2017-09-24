/*
Question:
338. Counting Bits  QuestionEditorial Solution  My Submissions
Total Accepted: 33860
Total Submissions: 58925
Difficulty: Medium
Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].

Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.


*/

Best Solution: Dynamic Programming:

public class Solution {
    
    /*
    This uses the hint from the description about using ranges. Basically, the numbers in one range are equal to 1 plus all of the numbers in the ranges before it. If you write out the binary numbers, you can see that numbers 8-15 have the same pattern as 0-7 but with a 1 at the front.

My logic was to copy the previous values (starting at 0) until a power of 2 was hit (new range), at which point we just reset the t pointer back to 0 to begin the new range.
    */
    
    
    public int[] countBits(int num) {
        //DP solution
        int[] result = new int[num+1];
        result[0] = 0;
        int pow = 1;
        int t=0;
        for(int i=1 ; i < num+1 ; i++,t++)
        {
            if(i == pow)
            {
                pow = pow*2;
                t=0;
            }
            result[i] = result[t]+1;
        }
        return result;
    }
}




Solution 2:

Running Time: O(n*sizeof(integer))

public class Solution {
    public int[] countBits(int num) {
        int[] result = new int[num+1];
        for(int i = 0 ; i <= num ; i++)
        {
            int m = i;
            int count = 0;
            while(m>0)
            {
                count = count + (m%2);
                m = m >>> 1;
            }
            result[i] = count;
        }
        return result;
    }
}


Solution 2:






Note:

1....
2....
3....