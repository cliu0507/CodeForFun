# Given an array and an index to an element in the array, partition the array such that all the 
# smaller elements are moved to the left, and elements which are equal or bigger are moved to the right.

# Sample Data-
# Input -> array is  [2, 4, 1, 6, 7, -9, -3, 0], index 0
# Output -> [1, -3, 0, -9, 2, 4, 6, 7]

def swap(array, i,j):
    temp = array[i]
    array[i] = array[j]
    array[j] = temp
    

    # 0 <= start <= index <= end < len(arr)
def partition(array, index, start, end):
    if not array:
        return []
    
    #to move the pivot element to the end of array
    swap(array,index,end)
    
    pivot = array[end]
    
    i = start-1
    for j in range(start,end+1):
        if array[j] < pivot:
            i += 1
            swap(array,i,j)
            
    swap(array,i+1,end)
    return array,i+1
    
    
a = [2, 4, 1, 6, 7, -9, -3, 0]
print(partition(a,6,1,6))


# Sample Data-
# Input -> array is  [2, 4, 1, 6, 7, -9, -3, 0], start = 0, end = 4, index = 1 
# Output -> [2, 1, 4, 6, 7, -9, -3, 0]

# Input -> array is  [2, 4, 1, 6, 7, -9, -3, 0], start = 1, end = 6, index = 6
# Output -> [2, -9, -3, 4, 1, 6, 7, 0]


# 1 <= k <= len(arr)

#[2, 4, 1, 6, 7, -9, -3, 0]
#3 largest - 7, 6, 4

def largest_k(arr, k):
    
    
使用partition function, 找最大的k个elements:

问： worst case time complexity

问： 平均时间：我回答O(n) 但好像不对， 面试官说 看怎么implement,如果implement的好 可以有 klogn
