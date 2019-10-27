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


5. binary search 找左边界算法

例子 [1,2,4,4,4,6,7] target = 4 \
找第一个出现target , 前提是数组里存在target,return 2 \
```
def low_bound(nums,target):
    #basically the first element index >= target
    low = 0
    high = len(nums)-1
    while low < high:
        mid = (low + high)/2
        if nums[mid] >= target:
            high = mid
        else:
            low = mid + 1
    return low if nums[low] == target else -1
```
```
如果数组里没有这个target：low/high代表着什么？\

例子 [1,2,4,4,4,6,7] target = 3 \
	(0,3,6) -> (0,1,3) -> (2,2,3) -> (2,\,2) -> basically return 2, 代表需要插入的index
     [1,2,4,4,4,6,7] target = 0 \
     	(0,3,6) -> (0,1,3) -> (0,0,1) -> (0,\,0) -> basically return 0, 代表需要插入的index
     [1,2,4,4,4,6,7] target = 6.5 \
	(0,3,6) -> (4,5,6) -> （6,\,6)               basically return 6, 代表需要插入的index (和下面的矛盾)
     [1,2,4,4,4,6,7] target = 8 \
     	(0,3,6) -> (4,5,6) -> (6,\,6)               basically return 6, 代表需要插入的index + 1 不符合上面的规律！！！

总结：哪怕是python的bisect 功能也不支持 在没有target的情况下找到insert的位置；换句话说low,high没有意义！


发散： 
那么high/low 到底可以如何理解呢？请看下面的function
返回值代表的意义是：
数组中 到底有多少个元素 严格小于 target 

def low_bound2(nums,target):
    #basically the first element index >= target
    low = 0
    high = len(nums)-1
    while low < high:
        mid = (low + high)/2
        if nums[mid] >= target:
            high = mid
        else:
            low = mid + 1
    if nums[low] < target:
    	return low + 1
    return low


发散2: 刷leetcode时候发现另一种写法也可以：不知道为什么！
[1,2,3,4,4,6,7]
(0,3,6)
(0,1,2)
(1,1,2)
(2,2,2)
def low_bound3(nums,target):
    #basically the first element index >= target
    low = 0
    high = len(nums)-1
    while low <= high:
        mid = (low + high)/2
        if nums[mid] >= target:
            high = mid - 1
        else:
            low = mid + 1
    return low
```

6. binary search 找右边界算法

例子 [1,2,4,4,4,6,7] target = 4 \
找最后一个出现target , 前提是数组里存在target,return 4 \
```
def upper_bound(nums, target):
    low = 0
    high = len(nums) - 1
    while low < high:
        mid = (low + high)/2 + 1 #注意 这个条件是保障不死循环的！因为 nums[mid] == target的情况下会需要更新low， 
			         #如果还用 mid = (low+high)/2的话，当只有两个元素的时候，low和mid永远相等会进入死循环
        if nums[mid] <= target:
            low = mid
        else:
            high = mid - 1
    return high if nums[high] == target else -1
```

```
如果数组里没有这个target：low/high代表着什么？\

例子 [1,2,4,4,4,6,7] target = 3 \
	(0,4,6) -> (0,2,3) -> (1,2,2) -> (1,\,1)    basically return 1, 代表需要插入的index - 1 
     [1,2,4,4,4,6,7] target = 1.5 \
     	(0,4,6) -> (0,2,3) -> (0,1,1) -> (0,\,0)    basically return 0, 代表需要插入的index - 1 （和上面的矛盾）
     [1,2,4,4,4,6,7] target = 0 \
     	(0,4,6) -> (0,2,3) -> (0,1,1) -> (0,\,0)    basically return 0, 代表需要插入的index 
     [1,2,4,4,4,6,7] target = 8 \
     	(0,4,6) -> (4,6,6) -> (6,\,6)               basically return 6, 代表需要插入的index - 1 不符合上面的规律！！！

总结：哪怕是python的bisect 功能也不支持 在没有target的情况下找到insert的位置；换句话说low,high没有特别意义！

发散：leetcode 378
那么high/low 到底可以如何理解呢？请看下面的function
返回值代表的意义是：
数组中 到底有多少个元素 小于等于 target 

def upper_bound2(nums, target):
    low = 0
    high = len(nums) - 1
    while low < high:
        mid = (low + high)/2 + 1 #注意 这个条件是保障不死循环的！因为 nums[mid] == target的情况下会需要更新low， 
			         #如果还用 mid = (low+high)/2的话，当只有两个元素的时候，low和mid永远相等会进入死循环
        if nums[mid] <= target:
            low = mid
        else:
            high = mid - 1
    if nums[high] <= target: #重点！！！
        return high + 1
    return high

```

7. 数据规模
N = 12 基本上是 N! 算法的极限
N = 20000 基本上是 N^2 算法的极限
N = 1000 基本上支持 N^3 算法

8. 几种图的算法：
	1. dijkstra algorithm - single source all destination:
		time: O(N * N) if use adjacent matrix, O(Nlogn) if use heap
		space:O(N + E)
	2. Bellmon-Ford algorithm - single source all destination: (DP)
		time : O(N * E),
		space: O(NE)
		it supports negative edges
	3. Floyd-warshall (all pairs). any source to all destination
		time: O(N^3)
		space: O(N*N)

9.  
dijstra algorithm with heap:
```
import heapq


def calculate_distances(graph, starting_vertex):
    distances = {vertex: float('infinity') for vertex in graph}
    distances[starting_vertex] = 0

    pq = [(0, starting_vertex)]
    while len(pq) > 0:
        current_distance, current_vertex = heapq.heappop(pq)

        # Nodes can get added to the priority queue multiple times. We only
        # process a vertex the first time we remove it from the priority queue.
        if current_distance > distances[current_vertex]:
            continue

        for neighbor, weight in graph[current_vertex].items():
            distance = current_distance + weight

            # Only consider this new path if it's better than any path we've
            # already found.
            if distance < distances[neighbor]:
                distances[neighbor] = distance
                heapq.heappush(pq, (distance, neighbor))

    return distances


dijstra algorithm without heap:
https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/


example_graph = {
    'U': {'V': 2, 'W': 5, 'X': 1},
    'V': {'U': 2, 'X': 2, 'W': 3},
    'W': {'V': 3, 'U': 5, 'X': 3, 'Y': 1, 'Z': 5},
    'X': {'U': 1, 'V': 2, 'W': 3, 'Y': 1},
    'Y': {'X': 1, 'W': 1, 'Z': 1},
    'Z': {'W': 5, 'Y': 1},
}
```

10. Queue.Queue() \
q.qsize(). note:len(q)不存在


11. Heap in Python \
Python only has min heap \
If we need max heap, we need to put -value to heap, and apply "-1.0 * val" while retrieval

heapify(iterable) :- This function is used to convert the iterable into a heap data structure \
heappush(heap, ele) :- This function is used to insert the element mentioned in its arguments into heap \
heappop(heap) :- This function is used to remove and return the smallest element from heap.  \

```
import heapq
li = [5, 7, 9, 1, 3] 
heapq.heapify(li)  #inplace
print (list(li))  # print list
heapq.heappush(li,4) 
print(heapq.heappop(li))

```

12. Heap with Tuple/Prority Queue in Python \
you can use tuples, and it will sort by the first element of the tuple \
So if you don't want to (or can't?) do a __cmp__ method, you can manually extract your sorting key at push time \

Note: if there are three element tuple, then use second element if there is a tier in first element
```
>>> h = []
>>> heappush(h, (5, 'write code'))
>>> heappush(h, (7, 'release product'))
>>> heappush(h, (1, 'write spec'))
>>> heappush(h, (3, 'create tests'))
```

13. Segment tree:
Segment tree 比 树状数组应用场景的多。 而且好理解
https://zxi.mytechroad.com/blog/sp/segment-tree-sp14/
