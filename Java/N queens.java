/*
Question:
51. N-Queens  
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]

*/

Note: Row and Column and Diagonal1 and Diagonal2 --- 4 HashMap
DFS Idea:

public class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> line = new ArrayList<String>();
        HashMap<Integer,Boolean> row = new HashMap<Integer,Boolean>();
        HashMap<Integer,Boolean> column = new HashMap<Integer, Boolean>();
        HashMap<Integer,Boolean> diagonal1 = new HashMap<Integer,Boolean>();
        HashMap<Integer,Boolean> diagonal2 = new HashMap<Integer, Boolean>();
        for(int i = 0 ; i < n ; i++)
        {
            row.put(i, false);
            column.put(i,false);
        }
        for(int i = 0 ; i < n ; i++)
        {
            for(int j = 0 ; j < n ; j++)
            {
                diagonal1.put(i+j,false);
                diagonal2.put(i-j,false);
            }
        }
        DFS(list,line,row,column,diagonal1,diagonal2,n,0);
        return list;
    }
    
    public void DFS(List<List<String>> list, List<String> line, HashMap row, HashMap column, HashMap diagonal1, HashMap diagonal2, int n , int level)
    {
        if(level >= n)
        {
            List<String> copy = new ArrayList<String>(line);
            list.add(copy);
            return;
        }
        for(int i = 0 ; i < n ; i++)
        {
            if((boolean)row.get(level) == false && (boolean)column.get(i) == false && (boolean)diagonal1.get(i+level) == false && (boolean)diagonal2.get(level-i) == false)
            {
                line.add(StringProducer(i, n));
                row.put(level,true);
                column.put(i,true);
                diagonal1.put(i+level,true);
                diagonal2.put(level-i,true);
                DFS(list,line,row,column,diagonal1,diagonal2,n, level+1);
                row.put(level,false);
                column.put(i,false);
                diagonal1.put(i+level,false);
                diagonal2.put(level-i,false);
                line.remove(level);
            }
        }
    }
    
    public String StringProducer(int queenPosition, int n)
    {
        StringBuilder str = new StringBuilder();
        for(int i = 0 ; i < n-1 ; i++)
            str.append(".");
        str.insert(queenPosition,"Q");
        return str.toString();
    }
}




Another Solution from discussion:
This Method is also very good

The basic idea is to check possible columns row by row based on DFS: 
If the column is already used or the column is on 45° /135° diagonals with the placed queens, then ignore it, otherwise place a queen on it and keep DFS procedure. 
As the following:

public List<List<String>> solveNQueens(int n) {
	List<List<String>> res = new LinkedList<List<String>>();
	int[] usedCols = new int[n];// usedCols[i]: Column of the queen in row i
	Arrays.fill(usedCols, -1);
	DFS(usedCols, 0, res);
	return res;
}    

void DFS(int[] usedCols, int row, List<List<String>> res) {
	int n = usedCols.length;
	if (row == n) {
		res.add(drawGrids(usedCols));
		return;
	}     	

	// Check Possible columns for the inputed row.
	for (int col = 0; col < n; col++) {
		if (isValid(usedCols, row, col)) {
			usedCols[row] = col;
			DFS(usedCols, row + 1, res);// Move on to the next row	
		}
	}
}

// Check if the column is valid to place queen for the row.
boolean isValid(int[] usedCols, int row, int col) {
	for (int i = 0; i < row; i++) {
		// Excludes used columns and diagonal positions 
		// (x2-x1)/(y2-y1) == 1 or -1
		if (usedCols[i] == col || row - i == Math.abs(col - usedCols[i]))    			
			return false;
	}
	return true;
}

List<String> drawGrids(int[] usedCols) {
	List<String>res = new LinkedList<String>();
	for (int i : usedCols) {
    	char[] line = new char[usedCols.length];
    	Arrays.fill(line, '.');	    	
    	line[i] = 'Q'; 
    	res.add(String.valueOf(line));
	}    	
	return res;
}