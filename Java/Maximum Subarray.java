/*
Question:
53. Maximum Subarray   QuestionEditorial Solution  My Submissions
Total Accepted: 143674
Total Submissions: 376704
Difficulty: Medium
Contributors: Admin
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.

*/

Dynamic Programming: (Scan num[0 ~ i], store the maximum ending with num[i], then select maximum from all maximums)
O(n) time + O(1) space
public class Solution {
    public int maxSubArray(int[] nums) {
        int maximum = Integer.MIN_VALUE;
        int a = Integer.MIN_VALUE;
        for(int i = 0 ; i < nums.length ; i++)
        {
            if(a < 0)
            {
                a = nums[i];
            }
            else
            {
                a = (nums[i] > nums[i] + a)?nums[i]:(nums[i]+a);  
            }
            maximum = (a > maximum)?a:maximum;
            
        }
        return maximum;
    }
}

Solution 2:
Naive Divide and Conquer: (There is another subtle dc algorithim which has T(n) = 2T(n/2) + O(1))

T(n) = 2T(n/2) + n  
(leftsubarray, rightsubarray, subarray contains middle element)

The expression (left+right)/2 might cause overflow because destination type is int.
It is better to use left+(right-left)/2.

public class Solution {
    public int maxSubArray(int[] nums) {
        return maxSubArrayHelper(nums, 0 , nums.length-1);
    }
    
    public int maxSubArrayHelper(int[] nums, int start, int end)
    {
        if(start == end)
            return nums[start];
        else if(start>end)
        {
            return Integer.MIN_VALUE;
        }
        else
        {
            int middle = (start + end)/2;
            int leftMax = maxSubArrayHelper(nums , start , middle-1);
            int rightMax = maxSubArrayHelper(nums , middle+1,end);
            int middleMax = nums[middle];
            int temp = nums[middle];
            for(int i = middle-1 ; i >= start ; i--)
            {
                temp = nums[i] + temp;
                middleMax = temp > middleMax?temp:middleMax;
            }
            temp = middleMax;
            for(int i = middle+1 ; i <= end ;i++ )
            {
                temp = nums[i] + temp;
                middleMax = temp > middleMax?temp:middleMax;
            }
            return Math.max(Math.max(leftMax,rightMax), middleMax); 
        }
    }
}

Solution 3: Best divide and conquer:
Divider and conquer solution can achieve O(N) by returning the max prefix subarray starting with left, max suffix array ending with right, and the sum[left..right].

int maxSubArray(int A[], int n) {
    if(n == 0)
        return 0;
    else if(n == 1)
        return A[0];
   
    int maxSub = INT_MIN;
    int leftPrefix = INT_MIN, rightSuffix = INT_MIN, all = INT_MIN;
    maxSubArrayHelper(A, 0, n - 1, leftPrefix, rightSuffix, all, maxSub);
    return maxSub;
}

void maxSubArrayHelper(int A[], int left, int right, int & leftPrefix, int & rightSuffix, int & all, int &maxSub)
{
    if(left == right)
    {
        leftPrefix = A[left];
        rightSuffix = A[left];
        all = A[left];
        maxSub = max(maxSub, A[left]);
        return;
    }
    
    int middle = (left + right) / 2;
    int leftPrefix1, rightSuffix1, all1, leftPrefix2, rightSuffix2, all2;
    maxSubArrayHelper(A, left, middle, leftPrefix1, rightSuffix1, all1, maxSub);
    maxSubArrayHelper(A, middle + 1, right, leftPrefix2, rightSuffix2, all2, maxSub);

    // Use the returned leftPrefix, rightSuffix instead of walling through the elements between left and right
    leftPrefix = max(all1, all1 + leftPrefix2);
    leftPrefix = max(leftPrefix, leftPrefix1);
    rightSuffix = max(all2, all2 + rightSuffix1);
    rightSuffix = max(rightSuffix, rightSuffix2);
    all = all1 + all2;
    maxSub = max(leftPrefix, maxSub);
    maxSub = max(rightSuffix, maxSub);
    maxSub = max(rightSuffix1, maxSub);
    maxSub = max(leftPrefix2, maxSub);
    maxSub = max(rightSuffix1 + leftPrefix2, maxSub);
}


Note:

1....
2....
3....