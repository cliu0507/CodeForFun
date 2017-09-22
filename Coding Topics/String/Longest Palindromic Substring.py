Longest Palindromic Substring

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example:

Input: "babad"
Output: "bab"

Note: "aba" is also a valid answer.
Example:

Input: "cbbd"
Output: "bb"


方法1:

纯brute force
里面有O(n*n)种substring
每个substring check whether it is palindrome
O(n^3)


方法2:

O(n)扫一遍
以cur所在位置为seed像两边扩展
o(n*n)
注意seed可以是两个数中间也可以是一个数
所以要分奇数和偶数的情况

方法3
manacher algorithm




