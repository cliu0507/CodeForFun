Convert a Number to Hexadecimal
Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.

Note:

All letters in hexadecimal (a-f) must be in lowercase.
The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
The given number is guaranteed to fit within the range of a 32-bit signed integer.
You must not use any method provided by the library which converts/formats the number to hex directly.

class Solution(object):
    def toHex(self, num):
        """
        :type num: int
        :rtype: str
        """
        hexlist="0123456789abcdef"
        result=""
        if num == 0:return "0"
        i = 0 
        while(num!=0 and i < 8):
            result=hexlist[num & 15]+result
            num = num >> 4
            i+=1
        return result
        
        

Note: use i = 0 to 8 来记录32位int变成8位的hexdecimal
主要是用来handle 负数的two complement

在python里
-1 => fffffffffff  16个f
所以如果不设置8个位的那么每次右移一个F一共