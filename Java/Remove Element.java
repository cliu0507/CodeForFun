/*
Question:
Given an array and a value, remove all instances of that value in place and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

Example:
Given input array nums = [3,2,2,3], val = 3

Your function should return length = 2, with the first two elements of nums being 2.

*/


/*
This is a more efficient version which require less operations(because the order of elements can be changed) ==> different from remove zeros
*/
Solution1
public class Solution {
    public int removeElement(int[] A, int elem) {
    int l = A.length;
    for (int i=0; i<l; i++) {
        if (A[i] == elem) {
            A[i--] = A[l-- -1];
        }
    }
    return l;
}
}



/*
Two pointer solution, which is same as "REMOVE ZEROS"

*/
Solution2
public class Solution {
    public int removeElement(int[] nums, int val) {
        int p = 0;
        for(int i = 0 ; i<nums.length ; i++)
        {
            if(nums[i]!=val)
            {
                nums[p] = nums[i];
                p++;
            }
        }
        return p;
    }
}

*/


Note:

1....
2....
3....