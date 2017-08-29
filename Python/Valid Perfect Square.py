Valid Perfect Square

Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Returns: True
Example 2:

Input: 14
Returns: False


Newton Method:

class Solution(object):
    def isPerfectSquare(self, num):
        """
        :type num: int
        :rtype: bool
        """
        r = num
        while(r*r > num):
            r = (r+num/r)/2
        return r*r == num

Note it has to been integer, otherwise it will TLE(it will converge after several iteration)
这里我们只要考虑interger的情况就可以了
https://en.wikipedia.org/wiki/Integer_square_root
Using only integer division


Standard way of doing it:
class Solution(object):
    def isPerfectSquare(self, num):
        """
        :type num: int
        :rtype: bool
        """
        if num < 0: return False
        if num <= 1: return True
        n = num/2  # start guessing using n = num/2
        while n*n!= num:
            inc = (num-n*n)/(2*n)
            n += inc
            if -1 <= inc <= 1: break
        if n*n < num: n+=1
        if n*n > num: n-=1
        return n*n == num