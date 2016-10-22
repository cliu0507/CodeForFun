/*
Generate Parentheses

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

*/
Important!!!!:

Note that it is not correct to use recursive add "()" into different position of previous String list,
Reason is that it will have duplicated items.
Example: "()"+()()  and ()()+"()" 

Wrong Solution:
public class Solution { //It is wrong , has duplicated items
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        helper(n, result);
        return result;
    }


    public void helper(int n , List<String> result)
    {
        if(n == 0)
            return;
        else if(n == 1)
        {
            String str = "()";
            result.add(str);
        }
        else
        {
            helper(n-1 , result);
            List<String> copy = new ArrayList<String>(result);
            result.clean();
            for(int i = 0 ; i < copy.size() ; i++)
            {
                String str1 = "()"+copy.get(i);
                String str2 = "("+copy.get(i)+")";
                String str3 = copy.get(i)+"()";
                result.add(str1);
                result.add(str2);
                result.add(str3);
            }
        }
    }
}



Correct Solution !!!!!:
/*
BFS: The idea is intuitive. Use two integers to count the remaining left parenthesis (n) and the right parenthesis (m) . 
At each function call add a left parenthesis if n >0 and add a right parenthesis if m>n. 
Append the result and terminate recursive calls when both m and n are zero.
*/

public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        helper(result, new String("") , n , n , n);
        return result;
    }
    
    public void helper(List<String> list, String str , int left_remain , int right_remain, int n)
    {
        if(left_remain==0 && right_remain==0 )
        {
            list.add(str);
            return;
        }
        else
        {
            if(left_remain > 0)
                helper(list, str+"(" , left_remain-1 , right_remain,n);
            if(right_remain > left_remain)
                helper(list, str+")" , left_remain, right_remain-1,n);
        }
            
    }
}


/*
As we talked, placing () into different position may have duplicated, lets have new strategy:
it should place one "()" and add another one insert it but none tail it,

This is a DP solution:

First consider how to get the result f(n) from previous result f(0)...f(n-1).
Actually, the result f(n) will be put an extra () pair to f(n-1). Let the "(" always at the first position, to produce a valid result, we can only put ")" in a way that there will be i pairs () inside the extra () and n - 1 - i pairs () outside the extra pair.

Let us consider an example to get clear view:

f(0): ""

f(1): "("f(0)")"

f(2): "("f(0)")"f(1), "("f(1)")"

f(3): "("f(0)")"f(2), "("f(1)")"f(1), "("f(2)")"

So f(n) = "("f(0)")"f(n-1) , "("f(1)")"f(n-2) "("f(2)")"f(n-3) ... "("f(i)")"f(n-1-i) ... "(f(n-1)")"
*/

DP solution:
public class Solution
{
    public List<String> generateParenthesis(int n)
    {
        List<List<String>> lists = new ArrayList<>();
        lists.add(Collections.singletonList(""));
        
        for (int i = 1; i <= n; ++i)
        {
            final List<String> list = new ArrayList<>();
            
            for (int j = 0; j < i; ++j)
            {
                for (final String first : lists.get(j))
                {
                    for (final String second : lists.get(i - 1 - j))
                    {
                        list.add("(" + first + ")" + second);
                    }
                }
            }
            
            lists.add(list);
        }
        
        return lists.get(lists.size() - 1);
    }
}


