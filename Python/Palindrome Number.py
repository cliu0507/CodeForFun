
Determine whether an integer is a palindrome. Do this without extra space.

click to show spoilers.

Some hints:
Could negative integers be palindromes? (ie, -1)

If you are thinking of converting the integer to string, note the restriction of using extra space.

You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?

There is a more generic way of solving this problem.

Idea:
'''
compare half of the digits in x, so don't need to deal with overflow.
'''

class Solution(object):
    def isPalindrome(self, x):
        """
        :type x: int
        :rtype: bool
        """
        if x == 0 or x/10 ==0 :
            return True
        elif x < 0 or x%10 == 0 :
            return False
        else:
            #General Idea is to compare half of the number
            reverse = 0
            while(x!=0):
                tail = x%10
                reverse = reverse * 10 +tail
                x = x/10
                if reverse == x or reverse ==x/10 :
                    return True
            return False