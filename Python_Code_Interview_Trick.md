1. 如何用int作为binary表示是否index被used:
```
int used
for i in range(0,n):
	if (used & 1 << i):
		continue //代表第i个index没有剩余的，已经被使用了
	else:
		dfs(used,path,result)
```
followup: 如何改变used的第i个bit的表示:
```
used = used - (1<<i) 
这相当于将used的bit表示的第i位置为0
```

2. 2^n 和 n! 计算机可以做到多少n?
```
ans: 2^n 可以做到n = 20; n!一般也就做到n=10就可能溢出了
```


3. BFS 中如何遍历完一个完整level再跳出返回结果 （经典bfs一旦找到一个结果就会break for loop）：
```
idea: 使用一个found变量，再多加一个内部的for loop遍历一个整层level.：

q 是 queue
while (!q.empty() && !found) {
	for (int size = q.size(); size > 0; size--) {
		if 找到结果:
			found = True
			
	}
}
```

4. Binary Search 模版 1
leetcode No.35
找应该插入在哪个位置：假设没有重复 
```
class Solution(object):
    def searchInsert(self, nums, target):
        low = 0
        high = len(nums)-1
        while low <= high:
            mid = (low+high)/2
            if nums[mid] == target:
                return mid
            elif nums[mid] > target:
                high = mid - 1
            else:
                low = mid + 1
        return low
```
注意1： 为什么要return low 而不是high:
因为数组index的不对称性，例子：
[1,3,5] target = 4
	low = 0, high = 2, mid = 1: nums[mid] < target\
	low = 2, high = 2, mid = 2; nums[mid] > target\
	low = 2, high = 1, break , insert在 index = 2的位置

[1,3,5] target = 6
	low = 0, high = 2, mid = 1: nums[mid] < target\
	low = 2, high = 2, mid = 2; nums[mid] > target\
	low = 3, high = 2, break , insert在 index = 3的位置	\


注意2: 为什么不用左边界的search index算法：
因为左边界的search 返回的结果index只能是 [0,len(nums)-1],（因为搜索的结束条件是 low < end, 而不是low == end, 换句话说，low或者high无法被设置成len(nums)）
