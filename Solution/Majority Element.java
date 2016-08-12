/*
Question: Majority Element

Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.


*/




Solution 1 : Ideal Solution: Bit manipulation
O(n) Time Complexicity

// Bit manipulation 
public int majorityElement(int[] nums) {
    int[] bit = new int[32];
    for (int num: nums)
        for (int i=0; i<32; i++) 
            if ((num>>(31-i) & 1) == 1)
                bit[i]++;
    int ret=0;
    for (int i=0; i<32; i++) {
        bit[i]=bit[i]>nums.length/2?1:0;
        ret += bit[i]*(1<<(31-i));
    }
    return ret;
}




Solution 2
/* Hashtable 
O(n) running time and O(n) space
*/
public class Solution{
public int majorityElement(int[] nums) {
    Map<Integer, Integer> myMap = new HashMap<Integer, Integer>();
    //Hashtable<Integer, Integer> myMap = new Hashtable<Integer, Integer>();
    int ret=0;
    for (int num: nums) {
        if (!myMap.containsKey(num))
            myMap.put(num, 1);
        else
            myMap.put(num, myMap.get(num)+1);
        if (myMap.get(num)>nums.length/2) {
            ret = num;
            break;
        }
    }
    return ret;
}
}


Solution 3: Moore voting algorithm
/*
RUNNING TIME: O(n), Space O(1) ===>Moore voting algorithm
More decent method, use count variable to record how many times more for a majority element occured after substracting other elements
*/

public class Solution {
    public int majorityElement(int[] nums) {
        int major = nums[0];
        int count=1;
        for(int i = 1 ; i< nums.length ; i++)
        {
            if(count == 0)
            {
                major = nums[i];
                count++;
            }
            else if(nums[i] == major)
            {
                count++;
            }
            else
            {
                count--;
            }
        }
        return major;
    }
}


Solution 4: Sort
/*
First idea is to sort the array, so the majority element must be at the middle of the sorted array
Running Time: O(n*logn)
*/


public class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
}







Note:

1. Moore voting algorithm and bit manipulation are best
2....
3....