#https://www.geeksforgeeks.org/connected-components-in-an-undirected-graph/

# Python program to print connected
# components in an undirected graph
import collections


# note this is undirect graph

def dfs(graph, visited, node, path):
    visited.add(node)
    path.append(node)
    for next_node in graph[node]:
        if next_node not in visited:
            dfs(graph, visited,next_node, path)
def CC(edges):

    graph = collections.defaultdict(list)
    node_set = set()
    for u, v in edges:
        node_set.add(u)
        node_set.add(v)
        graph[u].append(v)
        graph[v].append(u)

    #print(graph)
    visited = set()

    res = []

    for node in node_set:
        if node not in visited:
            path = []
            dfs(graph, visited, node, path)
            res.append(path)
    print(res)


edges = [
    ('a','b'),
    ('a','c'),
    ('c','d'),
    ('e','f'),
    ('e','g'),
    ('m','n'),
    ('n','c')
]
CC(edges)