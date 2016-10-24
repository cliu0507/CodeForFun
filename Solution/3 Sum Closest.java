/*
Question: 16. 3Sum Closest
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).


*/

My accepted solution:
O(n*n) 
Sort+ Sweep using two pointers
public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int result;
        Arrays.sort(nums);
        int distance = Integer.MAX_VALUE;
        int targetSum = 0;
        for(int i = 0 ; i < nums.length ; i++)
        {
            if(i == 0 || (i > 0 && nums[i] != nums[i-1]))
            {
                int elem1 = nums[i];
                int sum = 0;
                int p = i+1;
                int q = nums.length-1;
                while(p < q )
                {
                    sum = nums[i] + nums[p] + nums[q];
                    if(sum == target)
                        return sum;
                    else
                    {
                        if(sum < target)
                            p++;
                        else
                            q--;
                        if(distance > Math.abs(sum-target))
                        {
                            distance = Math.abs(sum-target);
                            targetSum = sum;
                        }
                    }
                    
                }
            }
        }
        return targetSum;
    }
}


Solution 2:






Note:

1....
2....
3....