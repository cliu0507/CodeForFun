/*
Question:
62. Unique Paths

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

*/

Dynamic Programming:
public class Solution {
    public int uniquePaths(int m, int n) {
        int[][] sumPaths = new int[m][n];
        for(int i = 0 ; i < m ; i++)
        {
            for(int j = 0 ; j < n ; j++)
            {
                if(i == 0 && j == 0)
                    sumPaths[0][0] = 1;
                else if(i == 0)
                    sumPaths[0][j] = sumPaths[0][j-1] ;
                else if(j == 0)
                    sumPaths[i][0] = sumPaths[i-1][0] ;
                else
                    sumPaths[i][j] = sumPaths[i-1][j] + sumPaths[i][j-1];
            }
        }
        return sumPaths[m-1][n-1];
    }
}

Solution 2:
Math Way: Combination and Permutation
class Solution {
    public:
        int uniquePaths(int m, int n) {
            int N = n + m - 2;// how much steps we need to do
            int k = m - 1; // number of steps that need to go down
            double res = 1;
            // here we calculate the total possible path number 
            // Combination(N, k) = n! / (k!(n - k)!)
            // reduce the numerator and denominator and get
            // C = ( (n - k + 1) * (n - k + 2) * ... * n ) / k!
            for (int i = 1; i <= k; i++)
                res = res * (N - k + i) / i;
            return (int)res;
        }
    };




Note:

1....
2....
3....