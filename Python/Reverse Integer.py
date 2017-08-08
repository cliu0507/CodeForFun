Reverse Integer
Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

click to show spoilers.

Note:
The input is assumed to be a 32-bit signed integer. Your function should return 0 when the reversed integer overflows.


'''
Need to Consider Overflow though Python automatically extend 32 bit int to long if overflow
'''

class Solution(object):
    def reverse(self, x):
        """
        :type x: int
        :rtype: int
        """
        MAX_VALUE_INTEGER = pow(2,31) - 1
        MIN_VALUE_INTEGER = (-1)*MAX_VALUE_INTEGER - 1
        if x >= 0 :
            POSITIVE=True
        else:
            POSITIVE=False
            x = x * (-1)
        reverse = 0
        while(x != 0):
            reverse = reverse * 10 + x%10
            x = x/10
        if POSITIVE==True:
            result=reverse 
        else:
            result= reverse * (-1)
        if result > MAX_VALUE_INTEGER or result < MIN_VALUE_INTEGER:
            return 0
        else:
            return result