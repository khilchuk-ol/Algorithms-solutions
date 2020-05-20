import java.util.Stack;

import edu.princeton.cs.algs4.StdOut;

public class Board {

	private final int N;
	private final int[][] board;
	
    public Board(int[][] tiles) {
    	this(tiles, 0);
    }
    
    private Board(int[][] tiles, int moves) {
    	N = tiles.length;
    	board = new int[N][N];
    	
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			board[i][j] = tiles[i][j];
    		}
    	}
    }
                                           
    public String toString() {
       
    	StringBuilder str = new StringBuilder(dimension() + "\n");
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	
                str.append(board[i][j]);
                str.append(" ");
            }      
            str.append("\n");
        }  
        
        return str.toString();
    }

    public int dimension() {
    	return N;
    }

    public int hamming() {
    	int num = 0;
    	int ham = 0;
    	
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			num = i * N + j + 1;
    			
    			if (i == N - 1 && j == N - 1) break;
    			
    			if (board[i][j] != num) ham++;
    			num++;
    		}
    	}
    	
    	return ham;
    }

    public int manhattan() {
    	int manh = 0;
    	
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			if(board[i][j] == 0) continue;
    			
    			int iTmp = (board[i][j]-1) / N;
                int jTmp = (board[i][j]-1) % N;
                
                manh += (Math.abs(iTmp - i) + Math.abs(jTmp - j));
    		}
    	}
    	
    	return manh;
    }

    public boolean isGoal() {
    	int num = 1;
    	
    	if(board[N - 1][N - 1] != 0) return false;
    	
    	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	
                if (board[i][j] == 0) break;
                if (board[i][j] != num) return false;
                
                num++;
            }
        }
    	
        return true;
    }

    public boolean equals(Object y) {
    	if (y == null) return false;
    	if (y == this) return true;
    	
    	if (y.getClass() != this.getClass()) return false;
    	
    	Board that = (Board) y;
    	
    	if (that.dimension() != this.dimension()) return false;
    	
    	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.board[i][j] != (that.board)[i][j]) return false;
            }
        }
    	
        return true;
    }

    public Iterable<Board> neighbors() {
    	 Stack<Board> stack = new Stack<Board>();
         
         int row = 0, col = 0;
         int[][] dataTmp = new int[N][N];
         
         for (int i = 0; i < N; i++) {
             for (int j = 0; j < N; j++) {
            	 
                 dataTmp[i][j] = board[i][j];
                 
                 if (board[i][j] == 0) {
                     row = i; 
                     col = j;
                 }
             }
         }
         
         if (col > 0) {
        	 
             exch(dataTmp, row, col, row, col-1);
             stack.push(new Board(dataTmp));
             exch(dataTmp, row, col, row, col-1);
         }
         
         if (col < N-1) {
        	 
             exch(dataTmp, row, col, row, col+1);
             stack.push(new Board(dataTmp));
             exch(dataTmp, row, col, row, col+1);
         }
         
         if (row > 0) {
        	 
             exch(dataTmp, row, col, row-1, col);
             stack.push(new Board(dataTmp));
             exch(dataTmp, row, col, row-1, col);
         }
         
         if (row < N-1) {
        	 
             exch(dataTmp, row, col, row+1, col);
             stack.push(new Board(dataTmp));
             exch(dataTmp, row, col, row+1, col);
         }
         
         return stack;
    }

    public Board twin() {
    	 Board twin = new Board(board);
         
         if (twin.board[0][0] == 0) {
             exch(twin.board, 1, 0, 1, 1);
         } else if (twin.board[0][1] == 0) {
             exch(twin.board, 1, 0, 1, 1);
         } else {
             exch(twin.board, 0, 0, 0, 1);
         }
         return twin;
    }
    
    private void exch(int[][] matrix, int i, int j, int p, int q) {
        int tmp = matrix[i][j];  
        matrix[i][j] = matrix[p][q]; 
        matrix[p][q] = tmp;
    }
    
    public static void main(String[] args) {
    	int[][] input = new int[][]{{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        int[][] input3 = new int[][]{{0, 5, 7}, {1, 8, 4}, {3, 2, 6}};
  
        Board testBoard = new Board(input);
        Board testBoard3 = new Board(input3);
  
        StdOut.println(testBoard3.manhattan());
      
        Iterable<Board> result = testBoard.neighbors();
        
        for (Board b : result) {
            StdOut.println(b.toString());
        }
    }
}

