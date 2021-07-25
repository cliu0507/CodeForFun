import collections
from queue import Queue

'''
q.qsize(). note:len(q)不存在

'''

def bfs(graph, node, visited):
    q = Queue()
    q.put(node)

    while not q.empty():
        node = q.get()
        visited.add(node)

        # process current node here
        print(node)

        for next_node in graph[node]:
            if not next_node in visited:
                q.put(next_node)


def bfs_level(graph, node, visited):
    q = Queue()
    q.put(node)

    cur_level_count = 1
    level = 0

    while not q.empty(): # or while True
        next_level_count = 0
        i = 0
        while i < cur_level_count:
            node = q.get()
            visited.add(node)

            # process current node here
            print(node, level)

            for next_node in graph[node]:
                if not next_node in visited:
                    q.put(next_node)
                    next_level_count += 1
            i += 1
        cur_level_count = next_level_count
        level += 1
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
bfs(graph, 'a', visited)

visited = set()
bfs_level(graph, 'a', visited)