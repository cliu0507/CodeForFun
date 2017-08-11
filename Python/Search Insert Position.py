Search Insert Position


Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0


'''
Idea: Divide and Conquer

Note: if there is a duplicate, the code won't really work
'''

class Solution(object):
    
    def searchInsertHelper(self, nums, start, end, target):
        if(start > end):
            return start
        else:
            middle=(start+end)/2
            if nums[middle] == target:
                return middle
            elif nums[middle] > target:
                return self.searchInsertHelper(nums, start, middle-1, target)
            else:
                return self.searchInsertHelper(nums, middle+1, end, target)
            
    def searchInsert(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        return self.searchInsertHelper(nums, 0 , len(nums)-1, target)



How to handle duplicate?
去掉判断middle是否和target相等直接return的语句，如果num[mid] < target, low = mid + 1;如果num[mid] == target，high = mid-1,v因为这个数有可能存在于前面的index 是duplicate
如果num[mid] > target，high = mid-1

class Solution(object):
    
    def searchInsertHelper(self, nums, start, end, target):
        if(start > end):
            return start
        else:
            middle = (start + end)/2
            if nums[middle] < target:
                return self.searchInsertHelper(nums, middle+1, end, target)
            else:
                return self.searchInsertHelper(nums, start, middle-1, target)
            
    def searchInsert(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        return self.searchInsertHelper(nums, 0 , len(nums)-1, target)