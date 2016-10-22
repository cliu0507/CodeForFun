/*
10. Regular Expression Matching

Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
*/


Only String p has "*" or ".", also no head "*" , no consecutive "*", but can have consecutive "."


Note: 
while p.charAt(j-1) == s.charAt(i) or p.charAt(j-1) == '.'
table[i+1][j+1] = table[i][j+1]  //a* counts as multiple a
table[i+1][j+1] = table[i][j-1]  // a* counts as single a
table[i+1][j+1] = table[i+1][j-1]; // a* counts as empty



Dynamic Programming:

Transfer Formula: 

table[i][j]: whether substring s.substring(0,i+1) matches p.substring(0,j+1)

for(int i = 0 ; i < s.length() ; i++)
    for(int j = 0 ; j < p.length() ; j++)
    {
        if p.charAt(j) == s.charAt(i)
            table[i+1][j+1] = table[i][j];
        else if p.charAt(j) == '.'
            table[i+1][j+1] = table[i][j];
        else if p.charAt(j) == '*'
            {
                if p.charAt(j-1) == s.charAt(i)
                    table[i+1][j+1] = table[i][j+1] || table[i][j-1] || table[i+1][j-1];
                else if p.charAt(j-1) == '.'
                    table[i+1][j+1] = table[i][j+1] || table[i][j-1] || table[i+1][j-1];
                else 
                    table[i+1][j+1] = table[i+1][j-1];
    
            }      
        else
            ;

    }



public class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] table = new boolean[s.length()+1][p.length()+1];
        table[0][0] = true;

        for(int j = 0 ; j < p.length(); j++)
        {
            if(p.charAt(j) == '*' && table[0][j-1] == true) 
                table[0][j+1] = true;
        }

        for(int i = 0 ; i < s.length() ; i++)
            for(int j = 0 ; j < p.length() ; j++)
            {
                if (p.charAt(j) == s.charAt(i))
                    table[i+1][j+1] = table[i][j];
                else if (p.charAt(j) == '.')
                    table[i+1][j+1] = table[i][j];
                else if (p.charAt(j) == '*')
                    {
                        //table[i][j+1] --multiple characters
                        //table[i][j-1] -- single characters
                        //table[i+1][j-1] -- empty
                        if (p.charAt(j-1) == s.charAt(i))
                            table[i+1][j+1] = table[i][j+1] || table[i][j-1] || table[i+1][j-1];
                        else if (p.charAt(j-1) == '.')
                            table[i+1][j+1] = table[i][j+1] || table[i][j-1] || table[i+1][j-1];
                        else 
                            table[i+1][j+1] = table[i+1][j-1];
            
                    }      
                else
                    ;
                
            }
        return table[s.length()][p.length()];
    }
}


Recursive Version Solution:

public class Solution {
    public boolean isMatch(String s, String p) {
        
        if(p.length()==1)
        {
            if(s.length() != 1)
                return false;
            else
                return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')?true:false;
        }


        else if(p.charAt(1) == '*')
        {
            if((p.charAt(0) == s.charAt(0)) || (p.charAt(0) == '.'))
            {
                return (!s.isEmpty() && isMatch(s.substring(1), p.substring(0))) || isMatch(s, p.substring(2)) ;
            }
            else
            {
                return isMatch(s, p.substring(2));
            }

        }
        else
        {
            if((p.charAt(0) == s.charAt(0)) || (p.charAt(0) == '.'))
            {
                return (!s.isEmpty() && isMatch(s.substring(1),p.substring(1)));
            }
            else
                return false;
        }
        
    }
}