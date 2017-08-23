Intersection of Two Arrays II


Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.


Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

Assume nums1 : m length ; nums2 : n  


What if the given array is already sorted? How would you optimize your algorithm?
==>two pointers
O(m+n)

What if nums1's size is small compared to nums2's size? Which algorithm is better?
==>hash map
O(m+n) even without sorting


What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
==> binary search
O(logm * n)

