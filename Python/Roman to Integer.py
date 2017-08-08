#Roman to Integer

Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.

"""
Algorithm to convert Roman Numerals to Integer Number :

1.Split the Roman Numeral string into Roman Symbols (character).
2.Convert each symbol of Roman Numerals into the value it represents.
3.Take symbol one by one from starting from index 0:
If current value of symbol is greater than or equal to the value of next symbol, then add this value to the running total.
else subtract this value by adding the value of next symbol to the running total.
"""ß

#Solution:
class Solution(object):
    def romanToInt(self, s):
        """
        :type s: str
        :rtype: int
        """
        value_dict=dict()
        value_dict["I"] = 1
        value_dict["V"] = 5
        value_dict["X"] = 10
        value_dict["L"] = 50
        value_dict["C"] = 100
        value_dict["D"] = 500
        value_dict["M"] = 1000
        #Roman character should be in descending order
        result = 0
        for ind in range(len(s)):
            if ind >= len(s)-1: #this means it is the last digit
                result += value_dict[s[ind]]
            else:
                if value_dict[s[ind]] >= value_dict[s[ind+1]]:
                    result += value_dict[s[ind]]
                else:
                    result -= value_dict[s[ind]]
        return result