/*
Question: Nim Game

You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take the first turn to remove the stones.

Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can win the game given the number of stones in the heap.

For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones you remove, the last stone will always be removed by your friend.



*/

Solution 1:

public boolean canWinNim(int n) {
    if (n <= 0) {return false;}
    return n % 4 != 0;
}

Explanation:
According to the hint, we know that if n = 4, no matter how many stones I remove, I lose. If n = 5, I can remove one stone and there are 4 stones for another player. Thus, I win. Similarly, if n = 6 or 7, I can remove 2 or 3 stones and i win finally.
If n = 8, no matter how many stones I remove, there are 7 or 6 or 5 stones for another player, s/he can remove stones as we said before and then wins.
If n = 9 or 10 or 11, I can leave 8 stones to another player, then I win.
If n = 12, I can leave 9, 10 or 11 stones to another player. Then, s/he can leave 8 stones to me, then I lose.
......
The rule is: if (n % 4 == 0) then I lose.



Solution 2: (Dynamic Programming) BUT TIME EXCEED LIMIT
public class Solution {
    
    public boolean canWinNim(int n) {
        
         boolean a = true; 
         boolean b = true;
         boolean c = true;
         boolean d = true;
        if(n>0 && n<=3)
        {
            return true;
        }
        else
        {
            for(int i = 4 ; i <=n; i++)
            {
                d = (!a) || (!b) || (!c);
                a = b;
                b = c; 
                c = d;
            }
            return c;
        }
    }
}








Note:

1....
2....
3....