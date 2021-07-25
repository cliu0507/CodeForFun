import collections

'''
time complexity: 
total time: 
O(n * alpha(n))
alpha is log or slightly better than logn if using path compression
'''
def find(parent, i):
    if parent[i] == i:
        return i
    return find(parent, parent[i])


def union(parent, x, y):
    xset = find(parent, x)
    yset = find(parent, y)
    if xset != yset:
        parent[xset] = yset

'''
优化：
有一种优化可以balance
union find里面的tree
通过选择 parent[xset] = yset
还是parent[yest] = xset （大树接小数 还是小数接大树）
前提是需要有额外的data
structure来保存parent[xset]和parent[yset] 的树的size
一般用一个dict[i] = size来track和update
'''

edges = [
    ('a','b'),
    ('a','c'),
    ('c','d'),
    ('e','f'),
    ('e','g'),
    ('m','n'),
    ('n','c')
]

parent = collections.defaultdict(str)

# initialize parent
for u, v in edges:
    parent[u] = u
    parent[v] = v

for u, v in edges:
    union(parent, u, v)

print(parent)

print(find(parent,'b'))
print(find(parent,'c'))
print(find(parent,'m'))
print(find(parent,'e'))
print(find(parent,'f'))