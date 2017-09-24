/*
Question:
Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

*/


//This is the typical bitwise operation for XOR
//XOR has associative rule!!! A ^ B ^ C = A ^ (B ^ C)

Solution 1:
public class Solution {
    public int singleNumber(int[] nums) {
        int result=0;
        for (int i=0 ; i< nums.length ; i++)
        {
            result^=nums[i];
        }
        return result;
    }
}

Solution 2:






Note:

1....
2....
3....