Palindrome Partitioning

Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.
For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]

https://leetcode.com/problems/palindrome-partitioning/discuss/
DFS + DP

Method1:

DFS + Backtracking

O(2^n * n)


Method2:
DFS + Backtracking + DP(to check palindrome of substring)
O(2^n)

Method2

DFS + Backtracking(DP, then it will be O(n^2)) + DP(to check palindrome of substring)