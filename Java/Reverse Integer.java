/*
Question:Reverse Integer 
Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

Have you thought about this?
Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!

If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.

Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?

For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

Update (2014-11-10):
Test cases had been added to test the overflow behavior.
*/


有几个corner cases需要考虑
1. negative number
2. Integer Overflow: Example: 19999999999 => 99999999991
3. 0s at the head of integer

Solution 1:
public class Solution {
    public int reverse(int x) {
        String s;
        if(x > 0)
        {
            s = Integer.toString(x);
            int i=0;
            for(i = 0 ; i < s.length() ; i++)
            {
                if(s.charAt(i) != '0')
                    break;
            }
            s = s.substring(i);
            String result = new StringBuffer(s).reverse().toString();
            long value = (long)Long.valueOf(result);
            if (value > Integer.MAX_VALUE)
                return 0;
            else
                return (int)value;
        }
        else if(x < 0)
        {
            Integer temp = new Integer(x);
            long m = temp.longValue();
            s = Long.toString((long)(-1)*m);
            int i=0;
            for(i = 0 ; i < s.length() ; i++)
            {
                if(s.charAt(i) != '0')
                    break;
            }
            s = s.substring(i);
            String result = new StringBuffer(s).reverse().toString();
            long value = (long)Long.valueOf(result);
            if ((-1)*value < Integer.MIN_VALUE)
                return 0;
            else
                return (int)(value*(-1));
        }
        else
            return 0;
        
    }
}

Solution 2: best solution from discussion
public int reverse(int x)
{
    int result = 0;

    while (x != 0)
    {
        int tail = x % 10;
        int newResult = result * 10 + tail;
        if ((newResult - tail) / 10 != result) // Check Overflow
        { return 0; }
        result = newResult;
        x = x / 10;
    }

    return result;
}

Solution 3:
public int reverse(int x) {
        long rev= 0;
        while( x != 0){
            rev= rev*10 + x % 10;
            x= x/10;
            if( rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE)
                return 0;
        }
        return (int) rev;
    }



Note:

1....
2....
3....