/*
Shuffle an Array

Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();


*/


Solution: "Fisher–Yates shuffle" = "Knuth shuffle"


-- To shuffle an array a of n elements (indices 0..n-1):
for i from n−1 downto 1 do
     j ← random integer such that 0 ≤ j ≤ i
     exchange a[j] and a[i]

OR

-- To shuffle an array a of n elements (indices 0..n-1):
for i from 0 to n−2 do
     j ← random integer such that i ≤ j < n
     exchange a[i] and a[j]

java code:

public class Solution {
    
    Random rdn = new Random();
    int length;
    int[] nums;
    public Solution(int[] nums) {
        this.rdn = new Random();
        this.length = nums.length;
        this.nums = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] copy = new int[length];
        for(int i = 0; i< length; i++)
            copy[i] = nums[i];
        for(int j = 0 ; j < length ; j++)
        {
            int cur = rdn.nextInt(length-j);
            swap(copy , cur+j , j);
        }
        return copy;
        
    }
    
    public void swap(int[] copy, int m , int n)
    {
        int temp = 0;
        temp = copy[m];
        copy[m] = copy[n];
        copy[n] = temp;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */