# https://zxi.mytechroad.com/blog/sp/segment-tree-sp14/
# https://leetcode.com/problems/range-sum-query-mutable/

class Node(object):
    def __init__(self, start, end, sum, left=None, right=None):
        self.start = start
        self.end = end
        self.sum = sum
        self.left = left
        self.right = right


def build(start, end, nums):
    if start == end:
        return Node(start, end, nums[start])
    else:
        mid = (start + end) // 2
        left = build(start, mid, nums)
        right = build(mid + 1, end, nums)
        return Node(start, end, left.sum + right.sum, left, right)

def update(root, index, value):
    if root.start == index and root.end == index:
        root.sum = value
        return
    mid = (root.start + root.end) // 2
    if index <= mid:  # need to update leftside
        update(root.left, index, value)
    else:
        update(root.right, index, value)
    root.sum = root.left.sum + root.right.sum

def rangeQuery(root, i, j):
    # print i, j,
    if root.start == i and root.end == j:
        return root.sum
    mid = (root.start + root.end) // 2
    if j <= mid:
        return rangeQuery(root.left, i, j)
    elif i > mid:
        return rangeQuery(root.right, i, j)
    else:
        return rangeQuery(root.left, i, mid) + rangeQuery(root.right, mid + 1, j)


if __name__ == '__main__':
    arr = [1,2,3,4,5,6,7,8,9]
    tree = build(0, len(arr)-1, arr)
    print(rangeQuery(tree, 0, 8))
    print(rangeQuery(tree, 0, 4))
    print(rangeQuery(tree, 2, 8))
