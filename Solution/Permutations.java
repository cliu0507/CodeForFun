/*
Question: Permutations

Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

*/


Potential Idea:

1. Take any number as the first number and append any permutation of the other numbers. 
Difficulty: Track "other" numbers which haven't been used. Maybe need to have hashset (Then How To Deal with Duplicated Numbers?) => Hashmap dictionary?

2. Insert the next number anywhere in the already built permutations. (The Idea below solutions follow)




Thought:

The basic idea is, to permute n numbers, we can add the nth number into the resulting List<List<Integer>> from the n-1 numbers, in every possible position.

For example, if the input num[] is {1,2,3}: First, add 1 into the initial List<List<Integer>> (let's call it "answer").

Then, 2 can be added in front or after 1. So we have to copy the List<Integer> in answer (it's just {1}), add 2 in position 0 of {1}, then copy the original {1} again, and add 2 in position 1. Now we have an answer of {{2,1},{1,2}}. There are 2 lists in the current answer.

Then we have to add 3. first copy {2,1} and {1,2}, add 3 in position 0; then copy {2,1} and {1,2}, and add 3 into position 1, then do the same thing for position 3. Finally we have 2*3=6 lists in answer, which is what we want.



Iterative Solution:

public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> numlist = new LinkedList<>();
        if(nums!=null)
        {
            numlist.add(nums[0]);
            result.add(numlist);
        }
        else
            return result;
        for(int i = 1 ; i < nums.length; i++)
        {
            int listcount = result.size();
            for(int j = 0 ; j< listcount ; j++)
            {
                List cur_list = result.remove(0);
                
                int numcount = cur_list.size();
                for(int k = 0 ; k <= numcount; k++)
                {
                    numlist = new LinkedList<Integer>(cur_list);
                    numlist.add(k , nums[i]);
                    result.add(numlist);
                }
            }
            
        }
        return result;
        
    }
}




Recursive Solution:

public class Solution {
    public List<List<Integer>> permute(int[] nums) {
            
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> numlist = new ArrayList<Integer>();
        return permutehelper(nums, nums.length-1);
    }
    
    
    public List<List<Integer>> permutehelper(int[] nums, int cur)
    {
        if(cur == 0)
        {
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            List<Integer> numlist = new ArrayList<Integer>();
            numlist.add(nums[0]);
            result.add(numlist);
            return result;
        }
        else
        {
            List<List<Integer>> temp = new ArrayList<List<Integer>>();
            Iterator<List<Integer>> iter = permutehelper(nums , cur-1).iterator();
            while(iter.hasNext())
            {
                List<Integer> element = iter.next();
                for(int i = 0 ; i<= element.size() ; i++)
                {
                    List<Integer> numlist = new ArrayList<Integer>(element);
                    numlist.add(i, nums[cur]);
                    temp.add(numlist);
                }
            }
            return temp;
        }
    }
    
}




