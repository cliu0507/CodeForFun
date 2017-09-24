/*
Question: Guess Number Higher or Lower

We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number is higher or lower.

You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):

-1 : My number is lower
 1 : My number is higher
 0 : Congrats! You got it!
Example:
n = 10, I pick 6.

Return 6.

*/

Typical Binary Search Problem

Solution: My intial solution but TLE:(program is correct, but time exceed limit) =>strange

Note: Reason is (i + j) / 2 will cause integer overflow!!!!!
"i + (j - i) / 2" is faster than "(i + j) / 2" because the latter one could have integer overflow (becoming negative). that could result infinite loop...

/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

@@@@@@@mid = (end+start)/2 must be replaced by mid = start+(end-start)/2

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int start = 1;
        int end = n;
        int mid = (end+start)/2;
        int flag = guess(mid);
        while(flag!=0)
        {
            if(flag == -1)
            {
                start = start;
                end = mid-1;
                mid = (end+start)/2;
            }
            else
            {
                start = mid+1;
                end = end;
                mid = (end+start)/2;
            }
            flag = guess(mid);
        }
        return mid;
    }
}


Best Solution:

public class Solution extends GuessGame {
    public int guessNumber(int n) {
    int i = 1, j = n;
    while(i < j) {
        int mid = i + (j - i) / 2;
        if(guess(mid) == 0) {
            return mid;
        } else if(guess(mid) == 1) {
            i = mid + 1;
        } else {
            j = mid;
        }
    }
    return i;
}
}