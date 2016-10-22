/*
 Binary Watch
A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

Each LED represents a zero or one, with the least significant bit on the right.


For example, the above binary watch reads "3:25".

Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

*/

Best Solution from discussion: Brute force:

public List<String> readBinaryWatch(int num) {
    List<String> times = new ArrayList<>();
    for (int h=0; h<12; h++)
        for (int m=0; m<60; m++)
            if (Integer.bitCount(h * 64 + m) == num)
                times.add(String.format("%d:%02d", h, m));
    return times;        
}




First Solution:

First, 60 mins and 12 hours,
Hashmap<n , ArrayList<String> mins> min;
Hashmap<n, ArrayList<String> hours> hours;


Then min.get(num-j) concatenate hours.get(j)

public class Solution {
    public List<String> readBinaryWatch(int num) {
        HashMap<Integer, ArrayList<String>> min = new HashMap<Integer,ArrayList<String>>();
        HashMap<Integer, ArrayList<String>> hour = new HashMap<Integer, ArrayList<String>>();
        List<String> result = new ArrayList<String>();

    for(int i = 0 ; i < 60 ; i++)
    {
        int temp = i;
        int count_one = 0;
        while(temp != 0)
        {
            if( temp%2 != 0 )
                count_one++;
            temp = temp/2;
        }
        if(min.containsKey(count_one))
        {
            ArrayList<String> arraystr = min.get(count_one);
            if( i >= 10)
                arraystr.add(String.valueOf(i));
            else
                arraystr.add((new String("0"))+String.valueOf(i));
        }
        else
        {
            ArrayList<String> arraystr = new ArrayList<String>();
            if( i >= 10)
                arraystr.add(String.valueOf(i));
            else
                arraystr.add((new String("0"))+String.valueOf(i));
            min.put(count_one, arraystr);
        }
    }

    for(int i = 0 ; i < 12 ; i++)
    {
        int temp = i;
        int count_one = 0;
        while(temp != 0)
        {
            if( temp%2 != 0 )
                count_one++;
            temp = temp/2;
        }
        if(hour.containsKey(count_one))
        {
            ArrayList<String> arraystr = hour.get(count_one);
            arraystr.add(String.valueOf(i));
        }
        else
        {
            ArrayList<String> arraystr = new ArrayList<String>();
            arraystr.add(String.valueOf(i));
            hour.put(count_one , arraystr);
        }
    }

    for(int j = 0 ; j <= num ; j++)
    {
        if(min.containsKey(j) && hour.containsKey(num-j))
        {
            ArrayList arraystr1 = min.get(j);// 0-59
            ArrayList arraystr2 = hour.get(num-j); // 0-11
            Iterator<String> iter1 = arraystr1.iterator();
            while(iter1.hasNext())
            {
                String str1 = iter1.next();
                Iterator<String> iter2 = arraystr2.iterator();
                while(iter2.hasNext())
                {
                    result.add( iter2.next() + ":"+ str1);
                }
            }
        }

    }
    return result;

}
}
