
/*
Question: Integer Break
Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

Note: You may assume that n is not less than 2 and not larger than 58.
*/



/*
My initial idea:  WRONG WRONG WRONG!!!!

F(1) = 1;
F(2) = F(1)*1 = 1;
F(3) = F(1)*2 = 2;
F(4) = F(1)*3 = 3;
F(n) = max(F(n-1)*1, F(n-2)*2 , F(n-3)*3 ,...F(n-k) * k)

BUT IT IS ABSOLUTELY WRONG!!SINCE "at least two positive integers" 
F(4) = 2*2 = 4
But if follow my tranfer formula. It will be F(4) = F(1) * 3 =3 !!! 

*/
public class Solution ////Note that it is wrong!
{
	public int integerBreak(int n)
	{
		int[] array  = new int[n+1];
		array[0] = 0;
		array[1] = 1;
		for(int i = 2 ; i <= n ; i++)
		{
			int max = 0;
			for(int j = 1 ; j < i ; j++)
			{
				if(max < array[i-j]*j)
				{
					max = array[i-j] * j;  
				}
			}
			array[i] = max;

		}
		return array[n];	

	}

}


Correct Version:

CORRECT FORMULA: 
F(n) = max(F(n-j)* j , (n-j) * j)

F(n-j)* j  means using at least 2+1 = 3 numbers
(n-j) * j means using 2 numbers
so they will cover all cases

public class Solution ////Note that it is Correct
{
	public int integerBreak(int n)
	{
		int[] array  = new int[n+1];
		array[0] = 0;
		array[1] = 1;
		for(int i = 2 ; i <= n ; i++)
		{
			int max = 0;
			for(int j = 1 ; j < i ; j++)
			{
				if(max < Math.max(array[i-j]*j, (i-j)*j))
				{
					max = array[i-j] * j;  
				}
			}
			array[i] = max;

		}
		return array[n];	

	}

}


