/*
6. ZigZag Conversion

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".

*/


Solution: (Two pointers)
public class Solution {
    public String convert(String s, int numRows) {
        if(numRows == 0)
            return null;
        if(numRows == 1)
            return s;
        StringBuilder[] strlist = new StringBuilder[numRows];
        for(int i = 0 ; i<numRows ; i++)
            strlist[i] = new StringBuilder();
        int j = 0;
        for(int i = 0 ; i < s.length() ;)
        {
            if(j == 0) // obliquely up
            {
                for(  ; j < numRows-1 ; j++,i++)
                {
                    if(i < s.length())
                        strlist[j].append(s.charAt(i));
                    else
                        break;
                }
            }
            else if( j == numRows-1) // vertically down
            {
                for(  ;j >0 ; j--,i++)
                {
                    if(i < s.length())
                        strlist[j].append(s.charAt(i));
                    else
                        break;
                }
            }
            else
                break;
        }
        for(int i = 0 ; i < numRows-1 ; i++)
        {
            strlist[0] = strlist[0].append(strlist[i+1]);
        }
        return strlist[0].toString();
    }
}


Decent Solution from Discussion: Follow the same idea as above:

public String convert(String s, int nRows) {
    char[] c = s.toCharArray();
    int len = c.length;
    StringBuffer[] sb = new StringBuffer[nRows];
    for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();
    
    int i = 0;
    while (i < len) {
        for (int idx = 0; idx < nRows && i < len; idx++) // vertically down
            sb[idx].append(c[i++]);
        for (int idx = nRows-2; idx >= 1 && i < len; idx--) // obliquely up
            sb[idx].append(c[i++]);
    }
    for (int idx = 1; idx < sb.length; idx++)
        sb[0].append(sb[idx]);
    return sb[0].toString();
}

