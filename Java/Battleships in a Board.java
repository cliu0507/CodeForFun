/*
Question: 419. Battleships in a Board

Given an 2D board, count how many different battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:

You receive a valid board, made of only battleships or empty slots.
Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
Example:
X..X
...X
...X
In the above board there are 2 battleships.
Invalid Example:
...X
XXXX
...X
This is an invalid board that you will not receive - as battleships will always have a cell separating between them.


*/


O(1) Extra Space but need to modify the value of board

这个办法是扫描所有cell, board[i][j]再向下向右边扫描 把所有遇到的X 设置为“.”

public class Solution {
    public int countBattleships(char[][] board) {
        int res = 0;
        int row_count = board.length;
        int column_count = board[0].length;
        for(int i = 0 ; i < row_count ; i++)
        {
            for(int j = 0; j < column_count ; j++)
            {
                if(board[i][j] == 'X')
                {
                    res++;
                    board[i][j] = '.';
                    
                    int j_ind = j;
                    while(j_ind+1 < column_count && board[i][j_ind+1] == 'X')
                    {
                        board[i][j_ind+1] = '.';
                        j_ind++;
                    }
                    
                    int i_ind = i;
                    while(i_ind+1 < row_count && board[i_ind+1][j] == 'X')
                    {
                        board[i_ind+1][j] = '.';
                        i_ind++;
                    }
                }
            }
        }
        return res;
    }
}

Best Solution:(Very Subtle)

Going over all cells, we can count only those that are the "first" cell of the battleship. 
First cell will be defined as the most top-left cell. We can check for first cells by only counting cells that do not have an 'X' to the left and do not have an 'X' above them.

do it in one-pass, using only O(1) extra memory and without modifying the value of the board?
public class Solution {
    public int countBattleships(char[][] board) {
        int res = 0;
        for(int i = 0 ; i < board.length ; i++)
            for(int j =0 ; j < board[0].length ; j++)
            {
                if(board[i][j] == 'X')
                {
                    if((i==0||board[i-1][j] == '.') &&( j==0 || board[i][j-1] == '.' ))
                    {
                        res++;
                    }
                    
                }
            }
        return res;
    }
}






Note:

1....
2....
3....