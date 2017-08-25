Binary Watch

A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

Each LED represents a zero or one, with the least significant bit on the right.

For example, the above binary watch reads "3:25".

Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

Example:

Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
Note:
The order of output does not matter.
The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".



IDEA:

DFS + BACKTRACKING 
recursive get n-1 ' hour and minute
最后到一个子问题

[32,16,8,4,2,1] => 其中取n个值 一共有多少种和？
那么这就是一个combination的问题 
for 第一个值选中位置0 到 6:
	递归调用[16,8,4,2,1]中选 n-1个值 sum=sum+32



SIMILAR AS "combination and permutation"



class Solution(object):
    def readBinaryWatch(self, num):
        """
        :type num: int
        :rtype: List[str]
        """
        result = []
        
        for hour in range(0,num+1):
            minute = num-hour
            minuteRes = []
            hourRes =[]
            self.minuteGenerator(minute,minuteRes)
            self.hourGenerator(hour,hourRes)
            for hour in hourRes:
                if hour >= 12:
                    continue
                else:
                    for minute in minuteRes:
                        if minute >= 60:
                            continue
                        else:
                            result.append(str(hour)+":"+(str(minute) if minute >= 10 else ("0"+str(minute))))
        return result
            
            
    def minuteGenerator(self, minute, minuteRes):
        self.minuteGeneratorHelper(minute, 0, 0, minuteRes)
        
    def minuteGeneratorHelper(self, minute, position, sum, minuteRes):
        if minute == 0:
            minuteRes.append(sum)
            return
        
        minuteList = [32,16,8,4,2,1]
        for i in range(position,len(minuteList)):
            self.minuteGeneratorHelper(minute-1,i+1, sum + minuteList[i], minuteRes)
            
            

    def hourGenerator(self, hour, hourRes):
        self.hourGeneratorHelper(hour, 0, 0, hourRes)
        
    def hourGeneratorHelper(self, hour, position, sum, hourRes):
        if hour == 0:
            hourRes.append(sum)
            return
        
        hourList = [8,4,2,1]
        for i in range(position,len(hourList)):
            self.hourGeneratorHelper(hour-1,i+1, sum + hourList[i], hourRes)