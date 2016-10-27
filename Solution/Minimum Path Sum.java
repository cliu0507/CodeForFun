/*
Question:64.Minimum Path Sum

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

*/

O(row_count * col_count) Space
O(row_count * col_count) Time
public class Solution {
    public int minPathSum(int[][] grid) {
        if(grid == null) return 0;
        int row_count = grid.length;
        int col_count = grid[0].length;
        int[][] gridsum = new int[grid.length][grid[0].length];
        gridsum[0][0] = grid[0][0];
        for(int i = 0 ; i < row_count; i++)
        {
            for(int j = 0 ; j < col_count ; j++)
            {
                if(i == 0 && j == 0) 
                    continue;
                else if(i == 0 ) 
                {
                    gridsum[0][j] = gridsum[0][j-1] + grid[0][j];
                }
                else if(j == 0)
                {
                    gridsum[i][0] = gridsum[i-1][0] + grid[i][0];
                }
                else
                    gridsum[i][j] = Math.min(gridsum[i-1][j], gridsum[i][j-1]) + grid[i][j];
            }
        }
        return gridsum[row_count-1][col_count-1];
    }
}

Solution 2:
0 Space used: It overwrites grid. so will not use extra space
public int minPathSum(int[][] grid) {
	int m = grid.length;// row
	int n = grid[0].length; // column
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			if (i == 0 && j != 0) {
				grid[i][j] = grid[i][j] + grid[i][j - 1];
			} else if (i != 0 && j == 0) {
				grid[i][j] = grid[i][j] + grid[i - 1][j];
			} else if (i == 0 && j == 0) {
				grid[i][j] = grid[i][j];
			} else {
				grid[i][j] = Math.min(grid[i][j - 1], grid[i - 1][j])
						+ grid[i][j];
			}
		}
	}

	return grid[m - 1][n - 1];
}



Another TLE Dijkstra Algorithm:

The idea of Dijkstra algorithm is to divide the graph into 2 parts, visited and unvisited.
For every node in the visited part has a dist value. Then we need to exam every edges across the visited part and the unvisited parts, which are edges that its start node is in the visited part, while its end node is in the unvisited part. What we are looking for is one edge, which has the minimum value of (dist(start node) + the edge's value). Then we put this node into the visited part and exam the edges again.

Following is the code. It uses a Java Heap, PriorityQueue to keep track of the minimum (dist(start node) + the edge's value), but in this question, the edge value is in the node itself, which is the same for every edges ending to it, so actually the heap just keeps track of the mimimum dist(start node) of every unvisited nodes around the boarder between visited and unvisited.
public class Solution_dijkstra {

class PointComparator implements Comparator<int[]>{
	int[][] dist;
	public PointComparator(int[][] dist){
		this.dist = dist;
	}
	@Override
	public int compare(int[] o1, int[] o2) {
		int[] point1 = (int[])o1;
        int[] point2 = (int[])o2;
        return Integer.valueOf(dist[point1[0]][point1[1]])
            .compareTo(Integer.valueOf(dist[point2[0]][point2[1]]));
    }
}
	
public int minPathSum(int[][] grid) {
    if(grid == null || grid.length == 0) return 0;
    int m = grid.length;
    int n = grid[0].length;
    
    boolean[][] visited = new boolean[m][n];
    int[][] dist = new int[m][n];
    
    for(int x = 0; x < m; x++){
        for(int y = 0; y < n; y++){
            dist[x][y] = Integer.MAX_VALUE;
        }
    }
    
    dist[0][0] = grid[0][0];
    
    PriorityQueue<int[]> pq = new PriorityQueue<int[]>( m*n, new PointComparator(dist));
    
    pq.add(new int[]{0, 0});
    
    while(!pq.isEmpty()){
        
        int[] point = pq.poll();
        int x = point[0];
        int y = point[1];
        int d = dist[x][y];
        
        if(x == n-1 && y == m-1){
            return d;
        }
        
        visited[x][y] = true;
        
        if((y+1 < n) && !visited[x][y+1]){
            dist[x][y+1] = min(
                dist[x][y+1],
                d + grid[x][y+1]);
            pq.add(new int[]{x, y+1});
        }
        
        if((x+1 < m ) && !visited[x+1][y]){
            dist[x+1][y] = min(
                dist[x+1][y],
                d + grid[x+1][y]);
            pq.add(new int[]{x+1, y});
        }
    }
    return 0;
    
}

private int min(int i1, int i2){
	return i1 < i2 ? i1 : i2;
}

Note:

1....
2....
3....