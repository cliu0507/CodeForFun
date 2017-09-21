Unique Binary Search Tree

Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

其实也是Dynamic programming:

正确的思路是，给定 n 个 node 之后，直接看 inorder 数组【1,
2, 3, 4 .. n-1, n】
那么选任意位置的元素为 root，都可以建出来一个 valid BST，
左子树为 index 左边的 subarray，右子树为 index 右边的
subarray.
于是这就变成了一个利用递归结构的“组合”问题了，解的数量左
右相乘。inorder 是 BST 的灵魂啊。

class Solution(object):
    def numTrees(self, n):
        """
        :type n: int
        :rtype: int
        """
        if n <=2:
        	return n

        elif n >2:
        	dp = [0] * (n+1)
        	dp[0] = 0
        	dp[1] = 1
        	dp[2] = 2
        	for i in range(3,n+1):
        		for j in range(0,i):
        			leftCount = j
        			rightCount = i - j -1
        			dp[i] += dp[leftCount] * dp[rightCount]
        	return dp[n]

