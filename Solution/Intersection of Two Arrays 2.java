/*
Question: 350. Intersection of Two Arrays II
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


*/
IMPORTANT QUESTION:!!!!

What if elements of nums2 are stored on disk, and the memory is
limited such that you cannot load all elements into the memory at
once?

Answer:
If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, read chunks of array that fit into the memory, and record the intersections.
If both nums1 and nums2 are so huge that neither fit into the memory, sort them individually (external sort), then read 2 elements from each array at a time in memory, record intersections.


Let m=nums1.size(), and n=nums2.size()

Solution 1: hashtable (using unordered_map).

time complexity: max(O(m), O(n))
space complexity: choose one O(m) or O(n) <--- So choose the
smaller one if you can

Solution 2: sort + binary search

time complexity: max(O(mlgm), O(nlgn), O(mlgn)) or max(O(mlgm),
O(nlgn), O(nlgm))
O(mlgm) <-- sort first array
O(nlgn) <--- sort second array
O(mlgn) <--- for each element in nums1, do binary search in nums2
O(nlgm) <--- for each element in nums2, do binary search in nums1
space complexity: depends on the space complexity used in your
sorting algorithm, bounded by max(O(m), O(n))


So if two arrays are already sorted, and say m is much smaller than n,
we should choose the algorithm that for each element
in nums1, do binary search in nums2,
so that the complexity is O(mlgn).
In this case, if memory is limited and nums2 is stored
in disk, partition it and send portions of nums2 piece
by piece. keep a pointer for nums1 indicating the
current position, and it should be working fine~

Another Idea if arrays are sorted, using two pointers will have O(m+n) time complexity





Solution:

Sort + two pointers (O(nlogn)

public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        int cur = 0;
        int[] res = new int[Math.max(nums1.length,nums2.length)];        
        while(i<nums1.length && j < nums2.length)
        {
            if(nums1[i] == nums2[j])
            {   
                    res[cur] = nums1[i];
                    cur++;
                    i++;
                    j++;
            }
            else if(nums1[i] > nums2[j])
                j++;
            else if(nums1[i] < nums2[j])
                i++;
        }

        int[] final_res = new int[cur];
        for(int k = 0 ; k < cur; k++)
            final_res[k] = res[k];
        return final_res;
    }
}

