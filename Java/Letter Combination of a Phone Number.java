/* Letter Combinations of a Phone Number
Question:
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

Phone diag Map is at https://leetcode.com/problems/letter-combinations-of-a-phone-number/

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

*/

总结：不论如何都需要3重循环 如果是递归则只用2重循环

Best solution from discuss:
Idea is the same as mine

    public List<String> letterCombinations(String digits) {
    LinkedList<String> ans = new LinkedList<String>();
    String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    ans.add("");
    for(int i =0; i<digits.length();i++){
        int x = Character.getNumericValue(digits.charAt(i));
        while(ans.peek().length()==i){
            String t = ans.remove();
            for(char s : mapping[x].toCharArray())
                ans.add(t+s);
        }
    }
    return ans;
} 



My Solution:

/*
Naive way, use a cur point to scan digits and add up character to result list. Recursive
Also Iterative is not hard.Just need to add an outer loop at helper function

*/
public class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<String>();
        helper(digits, 0 , results);
        return results;
    }
    
    public void helper(String digits, int cur , List<String> results)
    {
        if(cur >= digits.length())
            return;
        if(cur == 0)
        {
            List<Character> list = findChar((int)(digits.charAt(cur) - '0'));
            for(int i = 0 ; i < list.size();i++)
            {
                results.add(String.valueOf(list.get(i)));
            }
        }
        else
        {
            List<String> tempResults = new ArrayList<String>(results);
            results.clear();
            List<Character> list = findChar((int)(digits.charAt(cur)-'0'));
            for(int i = 0 ; i < tempResults.size(); i++)
            {
                String s = tempResults.get(i);
                for(int j = 0 ; j < list.size();j++)
                {
                    results.add(s + String.valueOf(list.get(j)));

                }   
            }
        }
        helper(digits, cur+1 , results);
    }
    
    public List<Character> findChar(int number)
    {
        List<Character> list = new ArrayList<Character>();
        if(number == 0)
        {
            list.add(' ');
            return list;
        }
        if(number == 1)
            return list;
        if(number<7 && number > 1)
        {
            char ch= (char)(3*(number-2)+'a');
            list.add(ch);
            list.add((char)(ch+1));
            list.add((char)(ch+2));
        }
        if(number==7)
        {    list.add('p');list.add('q');list.add('r');list.add('s');}
        if(number==8)
        {
            list.add('t');list.add('v');list.add('u');
        }
        if(number == 9)
        {
            list.add('w');list.add('x');list.add('y');list.add('z');
        }
        return list;
    }
}

Solution 2:






Note:

1....
2....
3....