/*
Combination_Sum_My_Change: Allow duplicated

Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set can contain duplicate combinations.(allow permution)
For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 

[
  [[1,1,6],[1,2,5],[1,7],[1,2,5],[1,7],[2,6]]
]

和combination_sum不同的地方在于 每个重复的元素都可以被使用1次，结果list并不需要去除冗余，理解为所以元素都是“不同的”
比如说 觉果中［1，1，6］和［1，1，6］是不同的， 分别是pick不同的“1”



*/



public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        
        recursive(candidates, 0 , target, result, combination);
        return result;
    }
    
    public void recursive(int[] array , int cur , int target ,List<List<Integer>> result, List<Integer> combination)
    {
        if(target == 0)
            result.add(combination);
        else if(target < 0)
            return;
        else
        {
            List<Integer> copy = new ArrayList<Integer>(combination);
            int next_Target = target - array[cur];
            copy.add(array[cur]);
            recursive(array, cur+1, next_Target, result, copy);
            if(cur+1 < array.length)
                recursive(array,cur+1,target,result,combination);

        }
    }
}