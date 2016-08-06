/*
Question:
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.

*/


/*
This is a very typical two pointers solution. One pointer to traverse whole array, with another records the position of non-zero value Shifting forward. Then we set last part of array to be all zeros

*/


public class Solution {
    public void moveZeroes(int[] nums) {
        int p = 0;
        for(int i = 0 ; i< nums.length ; i++)
        {
            if(nums[i]!=0)
            {
                nums[p] = nums[i];
                p++;
            }   
                
        }
        for( ;p<nums.length ;p++ )
            nums[p] = 0;
    }
}



Note:

1....
2....
3....