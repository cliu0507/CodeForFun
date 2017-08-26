Longest Palindrome

Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.


import collections
class Solution(object):
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: int
        """
        c = collections.Counter(s)
        result = 0
        usedSingle = False
        for key in c:
            if c[key] % 2 ==0:
                result += c[key]
            elif c[key] == 1 and usedSingle==False :
                result += 1
                usedSingle=True
            elif c[key]%2 != 0 and usedSingle == False:
                result += c[key]/2*2 +1
                usedSingle =True
            elif c[key]%2 != 0 and usedSingle == True:
                result += c[key]/2*2
            else:
                continue
                
        return result


def longestPalindrome(self, s):
    """
    :type s: str
    :rtype: int
    """
    ctmap = {}
    for c in s:
        if c not in ctmap:
            ctmap[c] = 1
        else:
            ctmap[c] += 1

    ret = 0
    singleCharFound = 0
    for key in ctmap:
        if ctmap[key] % 2 == 0:
            ret += ctmap[key]
        else:
            ret += ctmap[key] - 1
            singleCharFound = 1
    
    return ret + singleCharFound