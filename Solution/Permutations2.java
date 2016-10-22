/*
Permutations II

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

*/

It is a follow up of Permutation. New assumption is that list has duplicated elements

General Idea:

Use an extra boolean array " boolean[] used" to indicate whether the value is added to list.
Sort the array "int[] nums" to make sure we can skip the same value.
when a number has the same value with its previous, we can use this number only if his previous is used

NOTE:use a boolean array is similar as a hashmap
Also follow this idea can solove Permuation 1 very easily, but will use O(n) extra space

Solution:
public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums); // Sort Array
        List<Integer> list = new ArrayList<Integer>();
        if(nums.length == 0)
            return result;
        else
        {
            boolean[] used = new boolean[nums.length];
            recursion(nums, list , used , result); //Recursion
            return result;
        }
    }
    
    
    public void recursion(int[] nums, List<Integer> list, boolean[] used, List<List<Integer>> result)
    {
        if(list.size() == nums.length) // If DFS reach the leaf node
            result.add(list);
        else
        {
            for(int i = 0 ; i < nums.length ; i++)
            {
                List<Integer> copy = new ArrayList<Integer>(list); // Copy LIST and Used array ==> Important
                boolean[] used_copy = Arrays.copyOf(used, used.length);
                if(used_copy[i] == true)
                    continue; // if current element has been used already
                else if( i== 0)//handle i = 0 case
                {
                    copy.add(nums[0]);
                    used_copy[0] = true;
                    recursion(nums , copy , used_copy , result);
                }
                else if( nums[i] == nums[i-1] && used_copy[i-1] == true) // If previous element equals current one, and previous has been used.
                {
                    copy.add(nums[i]);
                    used_copy[i] = true;
                    recursion(nums , copy , used_copy , result);
                }
                else if(nums[i] != nums[i-1])//If not equal
                {
                    copy.add(nums[i]);
                    used_copy[i] = true;
                    recursion(nums , copy , used_copy , result);
                }
                else
                    continue;
            }
        }
    }
}




Another Idea:
 very similar to the Permutation I, we need to use a hash set to determine whether the new list is duplicate.
 Use List function toString to convert list to a string, then use hashset!

 List list = [1,2,3]

 thne list.toString() = "1,2,3"
 We could use hashset to remove duplicate



Another Idea: is to use NextPermutation function 

