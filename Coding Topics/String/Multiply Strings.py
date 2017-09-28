Multiply Strings

Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.

Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.

class Solution(object):
    def multiply(self, num1, num2):
        """
        :type num1: str
        :type num2: str
        :rtype: str
        """

        result = 0
        n = 0
        num1 = num1[::-1]
        num2 = num2[::-1]
        for n ,i in enumerate(range(len(num1))):
            product = 0
            for digit,j in enumerate(range(len(num2))):
                product += int(num1[i]) * (int(num2[j]) * (10 ** digit))
                
            result += (product) * (10 ** n) 
        return str(result)        