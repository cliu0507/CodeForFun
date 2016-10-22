/*
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
A solution set is: 
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

*/


注意 有两个idea

1. 标准答案的方法：
假设有n长的数组candidates
track(0, target) = track(1,target-candidates[0]) + track(2, target-candidates[1]) + track(3, target-candidates[2])+....track(n,target-candidates[i-1])

track(1, target－candidates[0]) 在只选取第一个元素的基础上，使用后面1到n-1个元素的combination

track(2, target-candidates[1])  在只选取第2个元素的基础上, 使用后吗2到n-1个元素的combination

track(3,target - candidates[2]) 在只选取第三个元素的基础上 使用3到n-1个元素的combination

这就是for loop的由来


public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
   List<List<Integer>> list = new LinkedList<List<Integer>>();
   Arrays.sort(candidates);
   backtrack(list, new ArrayList<Integer>(), candidates, target, 0);
   return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] cand, int remain, int start) {
   
   if(remain < 0) return; /** no solution */
   else if(remain == 0) list.add(new ArrayList<>(tempList));
   else{
      for (int i = start; i < cand.length; i++) {
         if(i > start && cand[i] == cand[i-1]) continue; /** skip duplicates */
         tempList.add(cand[i]);
         backtrack(list, tempList, cand, remain - cand[i], i+1);
         tempList.remove(tempList.size() - 1);
      }
   }
}
}

f(n) = f(1) + f(2) + ..+ f(n-1)  = 2^(n-1)



2.我本来的想法：

n长度的candidates数组
每个元素都有被选和选择的两个选项

track(0, target) = track(1,target-candidates[0]) ＋ track(1,target)

track(1,target-candidates[0])意思是0号元素被选择
track(1,target)意思是0号元素未被选择


所以总共是2*2*2*2... = 2^(n-1)


但是 这个方法不容易处理重复的元素 很难backtrack重复的list
如果要选的话 就需要hashmap来record次数

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
