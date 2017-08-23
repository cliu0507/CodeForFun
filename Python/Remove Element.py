Remove Element


Summary

This is a pretty easy problem, but one may get confused by the term "in-place" and thought it is impossible to remove an element from the array without making a copy of the array.

Hints

Try two pointers.
Did you use the property of "the order of elements can be changed"?
What happens when the elements to remove are rare?



public int removeElement(int[] nums, int val) {
    int i = 0;
    for (int j = 0; j < nums.length; j++) {
        if (nums[j] != val) {
            nums[i] = nums[j];
            i++;
        }
    }
    return i;
}



Intuition

Now consider cases where the array contains few elements to remove. For example, 
n
u
m
s
=
[
1
,
2
,
3
,
5
,
4
]
,
v
a
l
=
4
nums=[1,2,3,5,4],val=4. The previous algorithm will do unnecessary copy operation of the first four elements. Another example is 
n
u
m
s
=
[
4
,
1
,
2
,
3
,
5
]
,
v
a
l
=
4
nums=[4,1,2,3,5],val=4. It seems unnecessary to move elements 
[
1
,
2
,
3
,
5
]
[1,2,3,5] one step left as the problem description mentions that the order of elements could be changed.


public int removeElement(int[] nums, int val) {
    int i = 0;
    int n = nums.length;
    while (i < n) {
        if (nums[i] == val) {
            nums[i] = nums[n - 1];
            // reduce array size by one
            n--;
        } else {
            i++;
        }
    }
    return n;
}