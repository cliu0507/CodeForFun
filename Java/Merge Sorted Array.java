/*
88. Merge Sorted Array

Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.

*/


My Solution:

Starting from tail to header, push sorted item from end to start (One pass only!)

public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(m == 0 && n == 0)
            return;
        else
        {
            int j = m+n-1;
            int p = m-1;
            int q = n-1;
            while(p >= 0 || q >=0)
            {
                if(p < 0)
                {
                    nums1[j] = nums2[q];
                    j--;
                    q--;
                }
                else if( q < 0)
                {
                    nums1[j] = nums1[p];
                    j--;
                    p--;
                }
                else
                {
                    if(nums1[p] >= nums2[q])
                    {
                        nums1[j] = nums1[p];
                        j--;
                        p--;
                    }
                    else
                    {
                        nums1[j] = nums2[q];
                        j--;
                        q--;
                    }
                }
            }
            return;
        }
    }
}



Solution 2: More concise Solution from discussion:

public void merge(int A[], int m, int B[], int n) {
    int i=m-1, j=n-1, k=m+n-1;
    while (i>-1 && j>-1) A[k--]= (A[i]>B[j]) ? A[i--] : B[j--];
    while (j>-1)         A[k--]=B[j--];
}
