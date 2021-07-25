import collections

#https://leetcode.com/problems/course-schedule/discuss/659308/python-easy-topological-sort-template

def toposort(edges):
    # initialize your data structure for degree (dependency order) and graph
    graph = collections.defaultdict(list)
    degree = collections.defaultdict(int)
    node_set = set()

    # build graph and update degree from input
    for u, v in edges:
        graph[u].append(v)
        degree[v] += 1
        node_set.add(u)
        node_set.add(v)

    # collect all nodes with no dependency in a list
    lst_no_dep = [node for node in node_set if degree[node] == 0]

    # Topological sort
    result_order = []
    while lst_no_dep:
        node = lst_no_dep.pop()
        result_order.append(node)
        for node_depend in graph[node]:
            degree[node_depend] -= 1
            if degree[node_depend] == 0:
                lst_no_dep.append(node_depend)
    return result_order



'''
c     d
^ ^   ^
|  \  |
|   \ |
a --> b --> e

'''
edges = [
    ('a', 'b'),
    ('a', 'c'),
    ('b', 'd'),
    ('c', 'b'),
    ('b', 'e')
]

res = toposort(edges)
print(res)

edges = [
    ('a', 'b'),
    ('b', 'c'),
    ('c', 'a')
]

res = toposort(edges)
print(res)