/*
31. Next Permutation

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

*/


IDEA:
在当前序列中，从尾端往前寻找两个相邻元素，前一个记为first，后一个记为second，并且满足first 小于 second。
然后再从尾端寻找另一个元素number，如果满足first 小于number，即将第first个元素与number元素对调，并将second元素之后（包括second）的所有元素颠倒排序，即求出下一个序列

example:
6，3，4，9，8，7，1
此时 first ＝ 4，second = 9
从尾巴到前找到第一个大于first的数字，就是7
交换4和7，即上面的swap函数，此时序列变成6，3，7，9，8，4，1
再将second＝9以及以后的序列重新排序，让其从小到大排序，使得整体最小，即reverse一下（因为此时肯定是递减序列）
得到最终的结果：6，3，7，1，4，8，9

English explanation Version:

[6，3，4，9，8，7，1]
     i-1 i     k
(1) leftward find the first decreasing number @ index i - 1, (4)
(2) then nums[i:] must be rightward decreasing (9,8,7,1)
(3) leftward find the first number that is larger than i - 1, which is at k, (7)
(4) swap i - 1 with k, (6,3,7,9,8,4,1). we can see that nums[i:] will still be rightward decreasing (9,8,4,1)
(5) But we need them to be rightward increasing so that its the smallest after swapping, so we reversed nums[i:], which get the result (6,3,7,1,4,8,9)


It has swap() and reverse() function

public class Solution {
    public void nextPermutation(int[] nums) {
        int length = nums.length;
        if(length == 1 || length == 0)
            return;
        int i;
        for( i = length-1 ; i >=1 ; i--)
        {
            if(nums[i] > nums[i-1] )
                break;
        }
        if(i == 0)
        {
            reverse(nums,0);
        }
        else
        {
            int m = i-1;
            int n = i;
            int j;
            for(j = length-1 ; j >= i ; j--)
            {
                if(nums[j] > nums[m])
                    break;
            }
            int k = j;
            swap(nums, k , m);
            reverse(nums, n);
        }
    }
    
    public void swap(int[] nums, int k , int m)
    {
        int temp = nums[k];
        nums[k] = nums[m];
        nums[m] = temp;
    }
    
    public void reverse(int[] nums, int boundary)
    {
        int p = boundary;
        int q = nums.length-1;
        for( ;p < q ; p++,q--)
        {
            swap(nums,p,q);
        }
    }
}