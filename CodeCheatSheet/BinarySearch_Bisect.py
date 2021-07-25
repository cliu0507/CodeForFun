import bisect

# https://levelup.gitconnected.com/practical-algorithms-tier-0-binary-search-99508cc62fe4
#============================================================
'''
Given A Sorted integers and a target, Return

the index of the first element EQUALS to the target

Or

the insert position of the target if no equal element exists
'''

def bisect_left(arr, target):
    low, high = 0, len(arr)
    while low < high:
        mid = (low + high) // 2
        if target > arr[mid]:
            low = mid + 1
        else:
            high = mid
    return low

def bisect_left2(arr, target):
    return bisect.bisect_left(arr, target)



#============================================================

'''
Given A Sorted integers and a target, Return

the index of the first element STRICTLY larger than the target

if all elements are all smaller than target, the return value is the length of arr

'''
def bisect_right(arr, target):
    low, high = 0, len(arr)
    while low < high:
        mid = (low + high) // 2
        if target >= arr[mid]:
            low = mid + 1
        else:
            high = mid
    return high

def bisect_right2(arr, target):
    return bisect.bisect_right(arr, target)


arr = [5,6,7,7,9]
target = 8

print(bisect_left(arr,target))
print(bisect_left2(arr,target))
print(bisect_right(arr,target))
print(bisect_right2(arr,target))