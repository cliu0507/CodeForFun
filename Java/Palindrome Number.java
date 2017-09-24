/*
9. Palindrome Number


Determine whether an integer is a palindrome. Do this without extra space.

click to show spoilers.

Some hints:
Could negative integers be palindromes? (ie, -1)

If you are thinking of converting the integer to string, note the restriction of using extra space.

You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?

There is a more generic way of solving this problem.

*/

Important Notes:

1. Integer Overflow: like 2147447412 ---- Need to use long primitive type
2. Negative Number is not palindrome
3. No extra space, so can not convert int to string


My Accepted Answer:

public class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0) return false;
        long long_x =  x;
        long y = (long)Math.abs(long_x);
        long backup = y;
        long reminder = 0;
        long palindrome = 0;
        while(y!=0)
        {
            reminder = y%10;
            y = y/10;
            palindrome = palindrome * 10 + reminder;
        }
        
        return (palindrome == backup); 
        
    }
}


Another Sol. ==>compare half of the digits in x, so don not need to deal with overflow. 
public boolean isPalindrome(int x) {
    if (x<0 || (x!=0 && x%10==0)) return false;
    int rev = 0;
    while (x>rev){
        rev = rev*10 + x%10;
        x = x/10;
    }
    return (x==rev || x==rev/10);
}
