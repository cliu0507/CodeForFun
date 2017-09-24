/*
Question: 349. Intersection of Two Arrays

Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
Each element in the result must be unique.
The result can be in any order.

*/


Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
Each element in the result must be unique.
The result can be in any order.


Solution 1:

HashSet O(n) space
public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> resList = new ArrayList<Integer>();
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i = 0 ; i < nums1.length ; i++)
        {
            if(!set.contains(nums1[i]))
                set.add(nums1[i]);
        }
        for(int j = 0 ; j < nums2.length ; j++)
        {
            if(set.contains(nums2[j]))
            {
                resList.add(nums2[j]);
                set.remove(nums2[j]);
            }
        }
        int[] res = new int[resList.size()];
        for(int i = 0 ; i < resList.size(); i++)
        {
            res[i] = (int)(resList.toArray()[i]);
        }
        return res;
    }
}




Solution 2: Sort + Two Pointers ----O(nlogn)
public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        int cur = 0;
        int[] res = new int[Math.max(nums1.length,nums2.length)];        
        while(i<nums1.length && j < nums2.length)
        {
            if(nums1[i] == nums2[j])
                if(cur == 0 || nums1[i]!=res[cur-1])
                {
                    res[cur] = nums1[i];
                    cur++;
                    i++;
                    j++;
                }
                else
                {
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


Binary search Solution

Time complexity: O(nlogn)

public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums2);
        for (Integer num : nums1) {
            if (binarySearch(nums2, num)) {
                set.add(num);
            }
        }
        int i = 0;
        int[] result = new int[set.size()];
        for (Integer num : set) {
            result[i++] = num;
        }
        return result;
    }
    
    public boolean binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }
}




Note:

1....
2....
3....