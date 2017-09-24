/*
Question: 13. Roman to Integer
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.

*/


Solution 1:
public class Solution {
    public int romanToInt(String s) {
        int result = 0;
        for(int i = s.length()-1 ; i>=0 ; i--)
        {
            char cha = s.charAt(i);
            if(cha == 'I')
            {
                if(result < 5)
                    result+=1;
                else
                    result-=1;
            }
            else if(cha == 'V')
            {
                result+=5;
            }
            else if(cha == 'X')
            {
                if(result < 50)
                    result+=10;
                else
                    result-=10;
            }
            else if(cha == 'L')
            {
                result+=50;
            }
            else if(cha == 'C')
            {
                if(result < 500)
                    result+=100;
                else
                    result-=100;
            }
            else if(cha == 'D')
            {
                result+=500;
            }
            else
            {
                result+=1000;
            }
        }
        return result;
    }
}


Solution 2:
public static int romanToInt(String s) {
	int res = 0;
	for (int i = s.length() - 1; i >= 0; i--) {
		char c = s.charAt(i);
		switch (c) {
		case 'I':
			res += (res >= 5 ? -1 : 1);
			break;
		case 'V':
			res += 5;
			break;
		case 'X':
			res += 10 * (res >= 50 ? -1 : 1);
			break;
		case 'L':
			res += 50;
			break;
		case 'C':
			res += 100 * (res >= 500 ? -1 : 1);
			break;
		case 'D':
			res += 500;
			break;
		case 'M':
			res += 1000;
			break;
		}
	}
	return res;
}







Note:

1....
2....
3....