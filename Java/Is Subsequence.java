/*
Question: 392. Is Subsequence

Given a string s and a string t, check if s is subsequence of t.

You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
s = "abc", t = "ahbgdc"

Return true.

Example 2:
s = "axc", t = "ahbgdc"

Return false.

Follow up:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?

*/


Methods:
1. Greedy Algorithm
2. Dynamic Programming
3. Binary Search


Important Follow up:!!!!! ==> Binary Search will beat greedy
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?

Suppose s length is m, t length is n, n>>m

Answer: Binary Search Way beats greedy and DP

Greedy Alg: k* O(m+n)
Binary Searc < O(n) + k* O(mlogn) --Best
DP: k * O(m*n)


Time: O(m+n) 

FOLLOW UP CASE:
if there are k s strings. ==> O(m+n) *k

(Note if using binary search, it will be fast for this follow up case)

Greedy Algorithm Solution:
public class Solution {
    public boolean isSubsequence(String s, String t) {
        int p = 0;
        int q = 0;
        while(p < s.length() && q < t.length())
        {
            if(s.charAt(p) == t.charAt(q))
            {
                p++;
                q++;
            }
            else
            {
                q++;
            }
        }
        return (p == s.length());
    }
}

Dynamic Programming:
O(m * n) --- but Memory Limit Exceed
public class Solution {
    public boolean isSubsequence(String s, String t) {
        boolean[][] res = new boolean[s.length()][t.length()];
        if(s.length() == 0) return true;
        if(t.length() == 0) return false;
        res[0][0] = (s.charAt(0) == s.charAt(0));
        for(int j = 1; j < t.length(); j++)
        {
            res[0][j] = (s.charAt(0) == t.charAt(j))?true:res[0][j-1];
        }
        for(int i = 1 ; i < s.length() ; i++)
        {
            res[i][0] = false;
        }
        for(int i = 1 ; i < s.length() ; i++)
            for(int j = 1 ; j < t.length(); j++)
            {
                if(s.charAt(i) == t.charAt(j))
                    res[i][j] = res[i-1][j-1] || res[i][j-1];
                else
                    res[i][j] = res[i][j-1];
            }
        return res[s.length()-1][t.length()-1];
    }
}

Better Dynamic Programming: 
Use less memory: O(m) memory + O(mn) time

public class Solution {
    public boolean isSubsequence(String s, String t) {
        boolean[] mem1 = new boolean[t.length()];
        boolean[] mem2 = new boolean[t.length()];
        if(s.length() == 0) return true;
        if(t.length() == 0) return false;
        mem1[0] = (s.charAt(0) == t.charAt(0));
        for(int j = 1; j < t.length(); j++)
        {
            mem1[j] = (s.charAt(0) == t.charAt(j))?true:mem1[j-1];
        }

        for(int i = 1 ; i < s.length() ; i++)
        {
            for(int j = 1 ; j < t.length(); j++)
            {
                if(s.charAt(i) == t.charAt(j))
                    mem2[j] = mem2[j-1] || mem1[j-1];
                else
                    mem2[j] = mem2[j-1];
            }
            mem1 = mem2;
            mem2 = new boolean[t.length()];
        }   
        return mem1[t.length()-1];
    }
}


Better Binary Search Method:

Follow-up: O(N) time for pre-processing, O(Mlog?) for each S.
    // Eg-1. s="abc", t="bahbgdca"
    // idx=[a={1,7}, b={0,3}, c={6}]
    //  i=0 ('a'): prev=1
    //  i=1 ('b'): prev=3
    //  i=2 ('c'): prev=6 (return true)
    // Eg-2. s="abc", t="bahgdcb"
    // idx=[a={1}, b={0,6}, c={5}]
    //  i=0 ('a'): prev=1
    //  i=1 ('b'): prev=6
    //  i=2 ('c'): prev=? (return false)

Binary search will bet greedy if there are lots of s strings
Suppose k number of s strings.

Total time = O(N) + k * O(MlogM)

Note: Collections.binarySearch() function return value;
public class Solution {
    public boolean isSubsequence(String s, String t) {
        HashMap<Character, List<Integer>> hash = new HashMap<>();
        for(int i = 0 ; i < t.length(); i++)
        {
            if(!hash.containsKey(t.charAt(i)))
            {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                hash.put(t.charAt(i),list);
            }
            else
            {
                hash.get(t.charAt(i)).add(i);
            }
        }
        
        int preIndex = 0;
        for(int i = 0 ; i < s.length() ; i++)
        {
            if(hash.containsKey(s.charAt(i)))
            {
                int nextIndex = Collections.binarySearch(hash.get(s.charAt(i)), preIndex);
                if(nextIndex < 0)
                {
                    int insertion_point = (-1)* (nextIndex+1);
                    if(insertion_point == hash.get(s.charAt(i)).size())
                        return false;
                    nextIndex = insertion_point;
                }
                preIndex = hash.get(s.charAt(i)).get(nextIndex)+1;
            }
            else
                return false;
        }
        return true;
    }
}



Note:

1....
2....
3....