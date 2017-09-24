/*
Question:
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".

*/


Solution 1:
public class Solution {
    public String reverseVowels(String s) {
        char[] str = s.toCharArray();
        int i,j;
        int length = s.length();
        if(s.isEmpty())
            return s;
        else
        {
           i = 0;
           j = length-1;
           while(i<j)
           {
               if(isVowel(str[i]) && isVowel(str[j]))
               {
                   swap(str, i , j);
                   i++;
                   j--;
               }
               else if (isVowel(str[i]))
               {
                   j--;
               }
               else if (isVowel(str[j]))
               {
                   i++;
               }
               else
               {
                   i++;
                   j--;
               }
           }
           return new String(str);
        }
    }
    
    public boolean isVowel(char ch){
        if(Character.toUpperCase(ch) == 'A' || Character.toUpperCase(ch) == 'O' || Character.toUpperCase(ch) == 'I' || Character.toUpperCase(ch)=='U' || Character.toUpperCase(ch)=='E')
            return true;
        else
            return false;
        
    }
    
    public void swap(char[] string, int index1, int index2){
        char temp;
        temp = string[index1];
        string[index1] = string[index2];
        string[index2] = temp;
    }
}


Solution 2:


Loop Vowels String
String vowels = "aeiouAEIOU";
!vowels.contains(chars[start]+"")
!vowels.contains(chars[end]+"")


Note:

1....
2....
3....