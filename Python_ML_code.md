1. K Means

```
# K means clustering is applied to normalized ipl player data
import math
from collections import defaultdict

class K_Means(object):
	def __init__(self, k =3, tolerance = 0.00001, max_iterations = 500):
		self.k = k
		self.tolerance = tolerance
		self.max_iterations = max_iterations
        self.assignment = [[] for _ in range(3)]
        self.centroids = {}
        
        super().__init__()
        
    def euc_distance(self, point1, point2):
        return math.sqrt((point1[0] - point2[0]) ** 2 + (point1[1] - point2[1]) ** 2)
        
    
	def fit(self, data):
        self.centroids = defaultdict(list)
        
		#initialize the centroids, the first 'k' elements in the dataset will be our initial centroids
		for i in range(self.k):
			self.centroids[i] = data[i]

		#begin iterations
		for _ in range(self.max_iterations):
			self.classes = defaultdict(int)  # it stores {point_id: cluster_id}
            self.assignment = defaultdict(list)
            
			#find the distance between the point and cluster; choose the nearest centroid
			for i, point in enumerate(data):
                point_dis = []
                for centroid in self.centroids:
                    point_dis.append(self.euc_distance(centroid, point))
                self.classes[i] = point_dis.index(min(point_dis))
                self.assignments[self.classes[i]].append(i)
                

			prev_centroids = self.centroids
            
            self.centroids = {}
            
			#average the cluster datapoints to re-calculate the centroids
			for cluster_id, point_list in self.assignments.items():
                sum_x = sum([point[0] for point in point_list])
                sum_y = sum([point[1] for point in point_list])
                
                if len(point_list) != 0
                    self.centroids[cluster_id] = [sum_x / len(point_list), sum_y / len(point_list)]
                else:
                    self.centroids[cluster_id] = prev_centroids[cluster_id]
                
            # check whether reach convergence
			isOptimal = True

			for cluster_id in range(len(self.centroids)):

				prev_centroid = previous[cluster_id]
				curr_centroid = self.centroids[cluster_id]
                
                if self.euc_distance(prev_centroid, curr_centroid) >  self.tolerance:
					isOptimal = False

			#break out of the main loop if the results are optimal, ie. the centroids don't change their positions much(more than our tolerance)
			if isOptimal:
				break

	def pred(self, data):
		point_dis = []
        for centroid in self.centroids:
            point_dis.append(self.euc_distance(centroid, point))
		cluster_id = point_dis.index(min(point_dis))
        return cluster_id

def main():

	X = [
    [1, 1], [1.2, 1.4], [0.9, 0.8], [0.99, 1.02],
    [4,12], [4.2, 12.4],[3.9,11.8], [3.87, 11.74],
    [9, 0], [10,0], [9.5,0.2], [9.6, -0.1]
    ]
    
	
	km = K_Means(3)
	km.fit(X)
    
    print(km.pred([1.13,0.998]))
    print(km.pred([9.2,0.11]))
    print(km.pred([3.9,14.0]))

if __name__ == "__main__":
	main()
    

```
