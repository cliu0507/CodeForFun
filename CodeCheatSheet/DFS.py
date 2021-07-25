import collections

def dfs(graph, node, visited):
    visited.add(node)

    # process current node here:
    print(node)

    for next_node in graph[node]:
        if next_node not in visited:
            dfs(graph, next_node, visited)


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

graph = collections.defaultdict(list)
for edge in edges:
    graph[edge[0]].append(edge[1])

visited = set()
dfs(graph, 'a', visited)
