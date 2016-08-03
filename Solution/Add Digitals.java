/*
Question:
Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

*/

//Math Problem or A recursive solution


Solution 1: Naive Solution
public class Solution {
    public int addDigits(int num) {
        if (num<10)
            return num;
        else
        {
            return addDigits(addDigits(num/10)+num%10);
        }
    }
}


Solution 2:
//All about Math, go to wiki page https://en.wikipedia.org/wiki/Digital_root





