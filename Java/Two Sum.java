/*
Question: 1. Two Sum
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
UPDATE (2016/2/13):
The return format had been changed to zero-based indices. Please read the above updated description carefully.

*/

My solution: HashMap + O(n) time
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> hash = new HashMap<Integer,Integer>();
        for(int i = 0; i< nums.length ; i++)
        {
            if(hash.containsKey(nums[i]))
            {
                int[] result = new int[2];
                result[0] = (int)hash.get(nums[i]);
                result[1] = i;
                return result;
            }
            hash.put(target-nums[i] ,i);
            
        }
        return (new int[2]);
    }
}




Solution 2:






Note:

1....
2....
3....