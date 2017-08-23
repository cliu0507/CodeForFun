Plus One

Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.


class Solution(object):
    def plusOne(self, digits):
        """
        :type digits: List[int]
        :rtype: List[int]
        """
        num = 0
        for i in digits:
            num = 10 * num + i
        #Plus One
        num += 1
        result = []
        while(num!=0):
            result.insert(0, num%10)
            num = num/10
        return result