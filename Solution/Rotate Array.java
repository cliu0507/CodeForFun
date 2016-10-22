/*
Rotate Array

Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

[show hint]

Related problem: Reverse Words in a String II
*/


Important Note: Be aware of K > nums.length; We could use k = k%nums.length


Solution 1 (Best solution, inplace operation)


Example:  1234567  k = 3  =====> expected 5671234

1234567 =====> 7654321 ======> 567 + 1234


public class Solution {
    public void rotate(int[] nums, int k) {
    k %= nums.length;
    reverse(nums, 0, nums.length - 1);
    reverse(nums, 0, k - 1);
    reverse(nums, k, nums.length - 1);
}

public void reverse(int[] nums, int start, int end) {
    while (start < end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
        start++;
        end--;
    }
}
}







Solution 2:
Basic method to shift subarray(need temporary )

public class Solution {
    public void rotate(int[] nums, int k) {
        
        k = k% nums.length;
        
        int[] temp = new int[k];
        for(int i = 0 ; i < k ; i++)
            temp[k -i-1] = nums[nums.length-i-1];
        for(int i = nums.length-k-1 ; i >=0 ; i--) // Important order, easy to make mistake
            nums[i+k] = nums[i];
        for(int i = 0 ; i < temp.length; i++)
            nums[i] = temp[i];
    }
}



Solution 3:
Copy another array and concatenate the array with the tail of nums[]. 
Example: nums[] = 123456 ===> longer array: 123456123456
Then pick the correct part

BUT TLE

public class Solution {
    public void rotate(int[] nums, int k) {
        k = k %nums.length;
        int[] nums_double = new int[nums.length*2];
        for(int i = 0 ; i < nums.length ; i++)
        {
            nums_double[i] = nums[i];
            nums_double[nums.length+i] = nums[i];
        }
        for(int i = nums.length, j = 0; i < nums.length*2 ; i++ , j++)
        {
            nums[j] = nums_double[i-k];
        }

    }
}



