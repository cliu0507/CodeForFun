Rotate Array

Has to be in place !!!!

Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.


class Solution(object):
    def rotate(self, nums, k):
        k = k % len(nums)
        nums[:] = nums[-k:] + nums[:-k] 



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


Example:
let a= [1,2,3,4,5,6,7]
k = 3.

we have to first reverse the whole array by swapping first element with the last one and so on..
you will get[7,6,5,4,3,2,1]

reverse the elements from 0 to k-1
reverse the elements 7,6,5
you will get [5,6,7,4,3,2,1]

reverse the elements from k to n-1
reverse the elements 4,3,2,1
you will get[5,6,7,1,2,3,4]