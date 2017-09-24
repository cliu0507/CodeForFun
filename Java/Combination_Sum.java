
/*
39. Combination Sum
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [2, 3, 6, 7] and target 7, 
A solution set is: 
[
  [7],
  [2, 2, 3]
]

*/

/*
Sort the candidates and we choose from small to large recursively, every time we add a candidate to our possible sub result, we subtract the target to a new smaller one.
*/
public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> combination = new ArrayList<Integer>();
        /*
        HashSet set = new HashSet();
        for(int i = 0 ; i<candidates.length ; i++)
        {
            if(set.contains(candidates[i]))
                continue;
            else set.add(candidates[i]);
        }
        Iterator<Integer> iter = set.iterator();
        int[] sorted = new int[set.size()];
        int k = 0;
        while(iter.hasNext())
        {
            sorted[k] = iter.next();
        }
        Arrays.sort(sorted);
        
        recursive(sorted, target , 0 , result , combination);
        */
        Arrays.sort(candidates);
        recursive(candidates, target , 0 , result , combination);
        return result;    
    }
    
    public void recursive(int[] candidates, int target , int cur, List<List<Integer>> result, List combination)
    {
        if(target == 0)
        {
            result.add(combination);
            return;
        }
        
        for(int i = cur ; i < candidates.length ; i++)
        {
            int new_Target = target-candidates[i];
            if (new_Target >= 0) 
            {
                List<Integer> copy = new ArrayList<Integer>(combination);
                copy.add(candidates[i]);
                recursive(candidates, new_Target, i, result, copy);
            }
            else
                break;
        }
        
    }
    
}




Similar Python Code : DFS

def combinationSum(self, candidates, target):
    res = []
    candidates.sort()
    self.dfs(candidates, target, 0, [], res)
    return res
    
def dfs(self, nums, target, index, path, res):
    if target < 0:
        return  # backtracking
    if target == 0:
        res.append(path)
        return 
    for i in xrange(index, len(nums)):
        self.dfs(nums, target-nums[i], i, path+[nums[i]], res)