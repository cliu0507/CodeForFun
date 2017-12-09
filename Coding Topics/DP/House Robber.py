House Robber

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.



class Solution(object):
    def rob(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 0:
            return 0
        elif len(nums) == 1:
            return nums[0]
        elif len(nums) == 2:
            return max(nums[1],nums[0])
        else:
            mem = [0] * (len(nums) + 1)
            mem[0] = nums[0]
            mem[1] = max(nums[1],mem[0])
            for i in range(2,len(nums)):
                mem[i] = max(mem[i-1], mem[i-2] + nums[i])
            return mem[len(nums)-1]


Follow up - circle:

其实就是不直接考虑 rob(n)了 而是rob(n-1) =》 不抢劫第一家 和 不抢劫最后一家 两种情况
然后一起做optimal Solution


Follow up - tree house robber 3
主要是分开看左右子树做disjoint subproblem
然后左边/右边子节点抢或者不抢 分为两种情况 （就是隔着节点抢 只是取root）
然后做两种情况的max

class Solution(object):
    def rob(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        if root == None:
            return 0
        if root.left == None and root.right == None:
            return root.val
        leftSum = 0 
        rightSum = 0
        subleftSum = 0
        subrightSum = 0
        
        if root.left != None:
            leftSum = self.rob(root.left)
            subleftSum = self.rob(root.left.left) + self.rob(root.left.right)
        
        if root.right != None:
            rightSum = self.rob(root.right)
            subrightSum = self.rob(root.right.left) + self.rob(root.right.right)
        return max(subleftSum + subrightSum + root.val, leftSum + rightSum)