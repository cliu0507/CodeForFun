/*
Question:
15. 3Sum   
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]

*/

General Idea: First Sort array, then use two pointers(standard bi-directional 2Sum sweep of the remaining part of the array)
Note: Need to avoid duplicate:

Use:(p == i+1 || nums[p]!=nums[p-1])
A little trick: If did not find a match triplet, p++ or q--? ==>Choose p++

Solution 1:
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for(int i = 0 ; i < nums.length ; i++)
        {
            int elem1;
            int target;
            if(i == 0 || (i > 0 && nums[i]!= nums[i-1]))// avoid duplicate
            {
                elem1 = nums[i];
                target = 0 - elem1;
                int p = i+1;
                int q = nums.length-1;
                while( p < q)
                {
                    if(p == i+1 || nums[p]!=nums[p-1] )//avoid duplicate,只做了一个方向的avoid duplicate 也可以
                        if(nums[p] + nums[q] == target)
                        {
                            List<Integer> triplet = new ArrayList<Integer>();
                            triplet.add(nums[i]);
                            triplet.add(nums[p]);
                            triplet.add(nums[q]);
                            result.add(triplet);
                            p++;
                        }
                        else if(nums[p] + nums[q] > target)
                        {
                            q--;
                        }
                        else
                        {
                            p++;
                        }
                    else
                    {
                        p++;
                    }
                }
            }
        }
        return result;
    }
}

Solution from discussion: More easy to undersand, has two small while loop to avoid duplicate
一旦发现match,则用两个while loop 向内夹逼，如果没有发现 则正常p++,q--
public List<List<Integer>> threeSum(int[] num) {
    Arrays.sort(num);
    List<List<Integer>> res = new LinkedList<>(); 
    for (int i = 0; i < num.length-2; i++) {
        if (i == 0 || (i > 0 && num[i] != num[i-1])) {
            int lo = i+1, hi = num.length-1, sum = 0 - num[i];
            while (lo < hi) {
                if (num[lo] + num[hi] == sum) {
                    res.add(Arrays.asList(num[i], num[lo], num[hi]));
                    while (lo < hi && num[lo] == num[lo+1]) lo++;//avoid duplicate
                    while (lo < hi && num[hi] == num[hi-1]) hi--;//
                    lo++; hi--;
                } else if (num[lo] + num[hi] < sum) lo++;
                else hi--;
           }
        }
    }
    return res;
}






Note:

1....
2....
3....