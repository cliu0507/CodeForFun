import collections
import heapq
# K the given node (start node)

'''
Time:
O(V + ElogV)

Space:
O(V)

'''

def dijkstra(weights, node):
    graph = collections.defaultdict(list)
    node_set = set()
    for u, v, weight in edges:
        graph[u].append((weight,v))
        node_set.add(u)
        node_set.add(v)


    print(graph)
    heap = [(0, node)]
    visited = set()
    distance = {v:float('inf') for v in node_set}
    distance[node] = 0
    while heap:
        weight, node = heapq.heappop(heap)
        if node in visited:
            continue
        visited.add(node)
        if len(visited) == len(node_set):
            return distance
        for currWeight, v in graph[node]:
            if weight + currWeight < distance[v] and v not in visited:
                distance[v] = weight + currWeight
                heapq.heappush(heap, (weight + currWeight, v))
    return distance


'''
c     d
^ ^   ^
|  \  |
|   \ |
a --> b --> e

'''
edges = [
    ('a', 'b', 4),
    ('a', 'c', 2),
    ('b', 'd', 5),
    ('c', 'b', 1),
    ('b', 'e', 3),
    ('m', 'n', 10)
]

distance = dijkstra(edges, 'a')
print(distance)

distance = dijkstra(edges, 'm')
print(distance)