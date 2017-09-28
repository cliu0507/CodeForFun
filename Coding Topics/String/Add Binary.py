Add Binary

Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".

当问题非常简单的时候，解题重点就从优化时间复杂度变成
了优化代码简洁性

class Solution(object):
    def addBinary(self, a, b):
        """
        :type a: str
        :type b: str
        :rtype: str
        """
        i = len(a)-1
        j = len(b)-1
        carry = 0
        result = ""
        while(i >= 0 or j >=0 or carry == 1 ):
        	if i < 0:
        		digitA = 0
        	else:
        		digitA = int(a[i])
        		i-=1
        	if j < 0:
        		digitB = 0
        	else:
        		digitB = int(b[j])
        		j-=1
        	result+=(str((digitA+digitB+carry)%2))
        	carry = (digitA+digitB+carry)/2
        return result[::-1]
        


While loop 里三个 'OR' 的条件保证了三种不同情况下都会继
续读取，而其他两个自动 pad 0.