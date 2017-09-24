/*
Valid Parentheses

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

Subscribe to see which companies asked this question


*/




My Solution:
public class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0 ; i < s.length() ; i++)
        {
        	if(s.charAt(i) == '{' ||  s.charAt(i) == '(' || s.charAt(i) == '[')
        		stack.push(s.charAt(i));
        	else
        	{
        		if(stack.isEmpty() == True)
        			return false;
        		else
        		{
        			char ch = (char)stack.pop();
        			if ((ch == '{' && s.charAt(i) == '}') || (ch == '[' && s.charAt(i) == ']') || (ch == '(' && s.charAt(i) == ')'))
        				continue;
        			else
        				return false;
        		}
        	}
        }
        return stack.isEmpty();
    }
}