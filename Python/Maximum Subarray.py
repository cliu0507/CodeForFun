Maximum Subarray

Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.


#DP solution, O(n) space + O(n) time
import sys
class Solution(object):
    def maxSubArray(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 0:
            return 0
        else:
            table=[-sys.maxint-1] * len(nums)
            table[0] = nums[0]
            for i in range(1,len(nums)):
                table[i] = max([table[i-1] + nums[i],nums[i]])
            
            return max(table)
        


#Divide and Conquer:
T(n) = 2T(n/2) + O(n) = O(nlogn)

中间元素分割，O(n) component 来自于计算一定包含有middle item的最长sum的值

#Divide and Conquer
import sys
class Solution(object):
    def maxSubArray(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 1:
            return nums[0]
        elif len(nums) <=0:
            return -sys.maxint-1
        else:
            
            start = 0
            end = len(nums)-1
            middle = (end + start)/2
            
            #current sum equals moddle value
            cursum = nums[middle]
            maxwithmid = cursum
            
            for i in range(middle-1,start-1,-1):
                if i >= start and i <=end:
                    cursum += nums[i]
                    maxwithmid = max(cursum,maxwithmid)
                    
            cursum = maxwithmid
            for i in range(middle+1,end+1 , 1):
                if i >= start and i <=end:
                    cursum += nums[i]
                    maxwithmid = max(cursum,maxwithmid)       
            return max([self.maxSubArray(nums[:middle]), self.maxSubArray(nums[middle+1:]), maxwithmid])