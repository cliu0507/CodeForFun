import math
import heapq

def euc_distance(point1, point2):
    return math.sqrt((point1[0] - point2[0]) ** 2 + (point1[1] - point2[1]) ** 2)


def predict(data, x, K):
    heap = []
    for point in data:
        dis = euc_distance(point, x)
        heapq.heappush(heap, (dis, point))
    candidates = []
    i = 0
    while heap and i < K:
        _, point = heapq.heappop(heap)
        print(point)
        candidates.append(point[2])
        i += 1
    return sum(candidates) / float(len(candidates))



data = [
        [1, 1, 0],
        [1.2, 1.4, 0],
        [0.9, 0.8, 0],
        [0.99, 1.02, 0],
        [4, 12, 1],
        [4.2, 12.4, 1],
        [3.9, 11.8, 1],
        [3.87, 11.74, 1],
    ]

K = 2
x = [1.2, 1.3]
print(predict(data, x, K))