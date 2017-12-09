There is a fence with n posts, each post can be painted with one of the k colors. 
You have to paint all the posts such that no more than two adjacent fence posts have the same color. Return the total number of ways you can paint the fence.

The key to solve this problem is finding this relation.

f(n) = (k-1)(f(n-1)+f(n-2))

在刷第 n 块栅栏颜色的时候，要么要和 n - 1 的颜色不一样，要
么和 n - 2 的颜色不一样。
满足 “颜色不一样” 的选择，显然是 k - 1 种，于是
T[n] = (k - 1) * (T[n - 1] + T[n - 2]);


public class Solution {
public int numWays(int n, int k) {
if(n == 0) return 0;
if(n == 1) return k;
if(n == 2) return k * k;
int[] dp = new int[3];
dp[0] = 0;
dp[1] = k;
dp[2] = k * k;
for(int i = 3; i <= n; i++){
dp[i % 3] = (k - 1) * (dp[(i - 1) % 3] + dp[(i - 2)
% 3]);
}
return dp[n % 3];
}
}
