Valid Parenthesis


Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

class Solution(object):
	def isValid(self, s):
		stack = []
		i = 0
		dict = {"[":"]", "{":"}", "(":")"}		
		#Iterate string
		while(i < len(s)):
			if s[i] in dict:
				stack.append(s[i])
				i+=1
			#Meet right parenthesis, stack is not empty
			elif s[i] in dict.values() and len(stack) !=0 :
				if dict[stack.pop()] != s[i]:
					return False
				else:
					i+=1
			elif s[i] in dict.values() and len(stack) ==0 :
				return False
			else:
				i+=1				
		if len(stack) == 0:
			return True
		else:
			return False



class Solution:
    # @return a boolean
    def isValid(self, s):
        stack = []
        dict = {"]":"[", "}":"{", ")":"("}
        for char in s:
            if char in dict.values():
                stack.append(char)
            elif char in dict.keys():
                if stack == [] or dict[char] != stack.pop():
                    return False
            else:
                return False
        return stack == []