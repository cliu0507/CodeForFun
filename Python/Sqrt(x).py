Sqrt(x)

Implement int sqrt(int x).

Compute and return the square root of x.

Solution:
Newton Method

  	r = x
    while r*r > x:
        r = (r + x/r) / 2
    return r


Another Solution is bit maniputation? ? ? 