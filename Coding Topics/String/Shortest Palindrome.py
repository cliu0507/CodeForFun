Shortest Palindrome

Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd".


Brute Force Way:
我一开始的写法比较的简单粗暴，直接用写了一个 isPalindrome 函数去判断一个
substring 是不是 palindrome，然后从给定的 string 左面开始一直往右扫，去找到
从最左边字符开始，最长的 palindrome，然后把剩下的右边部分复制一份，翻转，
接到原来的 string 上.
但是超时了，时间复杂度太高。挂在了一个“aaaaaaaa...” 非常长但是结构非常简单
的 test case 上。
其实并没有用到substring的性质
如果是“aaaaaaaa...a"作为testcase
那么substring每次判断需要o(n)时间
所以总共需要O(n*n)的时间 非常的费时间


Better Method:
two pointer两边夹逼，如果不match，就i就调整为第一个字符,j就像前调整调整为j=j-1, end = end-1
最后0 ~ end就是从首字母开始最长的palindrome substring

class Solution(object):
    def shortestPalindrome(self, s):
        """
        :type s: str
        :rtype: str
        """
        if len(s) == 0:
            return ""
        else:
            i = 0
            j = len(s) - 1
            end = len(s) -1
            while(i<j):
                if s[i] == s[j]:
                    i+=1
                    j-=1
                else:
                    i = 0
                    end -= 1
                    j = end
            return s[end:][1:][::-1]+s
            
  
Best Method: KMP大杀器   



Another Cheating Method:
def shortestPalindrome(self, s):
    r = s[::-1]
    for i in range(len(s) + 1):
        if s.startswith(r[i:]):
            return r[:i] + s

Example: s = dedcba. Then r = abcded and I try these overlays (the part in (...) is the prefix I cut off, I just include it in the display for better understanding):

  s          dedcba
  r[0:]      abcded    Nope...
  r[1:]   (a)bcded     Nope...
  r[2:]  (ab)cded      Nope...
  r[3:] (abc)ded       Yes! Return abc + dedcba   