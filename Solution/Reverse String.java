/*344
Write a function that takes a string as input and returns the string reversed.

Example:
Given s = "hello", return "olleh".

*/




Solution1:
public class Solution {
    public String reverseString(String s) {
        char[] str = s.toCharArray();
        char temp;
        for (int i = 0 ; i < s.length()/2 ; i++)
        {
            temp = str[i];
            str[i] = str[s.length()-i-1];
            str[s.length()-i-1] = temp;
        }
        return new String(str);
    }
}


Solution2:
public class Solution {
	public String reverseString(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }
}






Note:

1. Convert a string object s to char array str

char[] str=  s.toCharArray();
//Converts this string to a new character array.

   charAt()
   int m = s.indexOf(String str)  //Returns the index within this string of the first occurrence of the specified substring.
   boolean boo = match(String regex) //Tells whether or not this string matches the given regular expression.

2. Convert a char array str to string object s

String s = new String(str);


3. StringBuilder is A mutable sequence of characters
useful methods : append(), toString(), reverse(), insert()


4. Static method for String Class:
String s = String.valueOf("This is a test string")
String s = String.valueOf(100)
