Climbing Stairs

You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.


Recursive Solution:
TLE:
class Solution(object):
    def climbStairs(self, n):
        """
        :type n: int
        :rtype: int
        """
        if n == 1:
            return 1
        elif n == 2:
            return 2
        elif n < 1:
            return 0
        else:
            return self.climbStairs(n-1) + self.climbStairs(n-2)


Recursive Solution with Memory

class Solution(object):
    table = None 
    
    def climbStairs(self, n):
        if self.table == None:
            self.table = [None] * n
        """
        :type n: int
        :rtype: int
        """
        if n == 1:
            return 1
        elif n == 2:
            return 2
        elif n < 1:
            return 0
        else:
            if self.table[n-1] == None:
                self.table[n-1] = self.climbStairs(n-1)
            if self.table[n-2] == None:
                self.table[n-2] = self.climbStairs(n-2)
            return self.table[n-1] + self.table[n-2]




Another neat solution in C++

public class Solution {

public int climbStairs(int n) {
    if(n == 0 || n == 1 || n == 2){return n;}
    int[] mem = new int[n];
    mem[0] = 1;
    mem[1] = 2;
    for(int i = 2; i < n; i++){
        mem[i] = mem[i-1] + mem[i-2];
    }
    return mem[n-1];
}
