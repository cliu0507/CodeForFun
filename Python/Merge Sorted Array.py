Merge Sorted Array

Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.




class Solution(object):
    def merge(self, nums1, m, nums2, n):
        """
        :type nums1: List[int]
        :type m: int
        :type nums2: List[int]
        :type n: int
        :rtype: void Do not return anything, modify nums1 in-place instead.
        """
        p = m-1
        q = n-1
        i = m+n-1
        while( p >=0 and q >= 0):
            if nums1[p] > nums2[q]:
                nums1[i] = nums1[p]
                p-=1
            else:
                nums1[i] = nums2[q]
                q-=1
            i-=1
        if p < 0:
            nums1[:i+1] = nums2[:q+1]
        else:
            nums1[:i+1] = nums1[:p+1]
                