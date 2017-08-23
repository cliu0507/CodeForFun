Count and Say


1.     1
2.     11
3.     21
4.     1211
5.     111221

"Count and Say problem" Write a code to do following:

n String to print
0 1
1 1 1
2 2 1
3 1 2 1 1
...
Base case: n = 0 print "1"
for n = 1, look at previous string and write number of times a digit is seen and the digit itself. In this case,

digit 1 is seen 1 time in a row... so print "1 1"

for n = 2, digit 1 is seen two times in a row, so print "2 1"

for n = 3, digit 2 is seen 1 time and then digit 1 is seen 1 so print "1 2 1 1"

for n = 4 you will print "1 1 1 2 2 1"

Consider the numbers as integers for simplicity. e.g. if previous string is "10 1" then the next will be "1 10 1 1" and the next one will be "1 1 1 10 2 1"


class Solution(object):
    def countAndSay(self, n):
        """
        :type n: int
        :rtype: str
        """
        if n <= 1:
            return "1"
        else:
            cur = None
            resultstr = ""
            count = 0
            for char in self.countAndSay(n-1):
                if cur == char:
                    count+=1
                elif cur != char and cur != None:
                    resultstr += str(count)
                    resultstr += cur
                    cur = char
                    count = 1
                else:
                    cur = char
                    count = 1
            
            resultstr += str(count)
            resultstr += cur
            return resultstr


neated version:
class Solution(object):
    def countAndSay(self, n):
        """
        :type n: int
        :rtype: str
        """
        if n <= 1:
            return "1"
        else:
            prev= self.countAndSay(n-1)
            cur = prev[0]
            resultstr = ""
            count = 1
            for char in prev[1:]:
                if cur == char:
                    count+=1
                else:
                    resultstr += str(count)
                    resultstr += cur
                    cur = char
                    count = 1
            resultstr += str(count)
            resultstr += cur
            return resultstr
