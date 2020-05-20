import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private final WeightedQuickUnionUF ufIsFull;
	private final WeightedQuickUnionUF ufPercolates;
	private final boolean[] buf;
	private final int n;

	private int opened = 0;

	// creates n-by-n grid, with all sites initially blocked
	public Percolation(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("Didn't work");
		}

		this.n = n;

		buf = new boolean[n * n + 2];
		ufIsFull = new WeightedQuickUnionUF(n * n + 2);
		ufPercolates = new WeightedQuickUnionUF(n * n + 2);
	}

	// opens the site (row, col) if it is not open already
	public void open(int row, int col) {
		if (row <= 0 || col <= 0 || row > n || col > n) {
			throw new IllegalArgumentException("Didn't work");
		}

		row--;

		if (buf[row * n + col]) {
			return;
		}

		buf[row * n + col] = true;
		opened++;

		unionWithAllPossible(row, col);
	}

	private void unionWithAllPossible(int actRow, int actCol) {
		if (actRow == 0) { 
			ufIsFull.union(0, actRow * n + actCol); 
			ufPercolates.union(0, actRow * n + actCol);
		}
		
		if (actRow == n - 1) { 
			ufPercolates.union(n * n + 1, actRow * n + actCol); 
		}
		
		if (actRow < n - 1 && buf[(actRow + 1) * n + actCol]) { 
			ufIsFull.union(actRow * n + actCol, (actRow + 1) * n + actCol); 
			ufPercolates.union(actRow * n + actCol, (actRow + 1) * n + actCol); 
		}
		
		if (actRow > 0 && buf[(actRow - 1) * n + actCol]) { 
			ufIsFull.union(actRow * n + actCol, (actRow - 1) * n + actCol); 
			ufPercolates.union(actRow * n + actCol, (actRow - 1) * n + actCol); 
		}
		
		if (actCol < n && buf[actRow * n + actCol + 1]) { 
			ufIsFull.union(actRow * n + actCol, actRow * n + actCol + 1); 
			ufPercolates.union(actRow * n + actCol, actRow * n + actCol + 1);
		}
		
		if (actCol > 1 && buf[actRow * n + actCol - 1]) { 
			ufIsFull.union(actRow * n + actCol, actRow * n + actCol - 1); 
			ufPercolates.union(actRow * n + actCol, actRow * n + actCol - 1);
		}
	}

	// is the site (row, col) open?
	public boolean isOpen(int row, int col) {
		if (row <= 0 || col <= 0 || row > n || col > n) {
			throw new IllegalArgumentException("Didn't work");
		}

		row--;

		return buf[row * n + col];
	}

	// is the site (row, col) full?
	public boolean isFull(int row, int col) {
		if (row <= 0 || col <= 0 || row > n || col > n) {
			throw new IllegalArgumentException("Didn't work");
		}

		row--; 

		return ufIsFull.find(row * n + col) == ufIsFull.find(0);
	}

	// returns the number of open sites
	public int numberOfOpenSites() {
		return opened;
	}

	// does the system percolate?
	public boolean percolates() {
		return ufPercolates.find(0) == ufPercolates.find(n * n + 1);
	}

	// test client (optional)
	public static void main(String[] args) {

		Percolation p = new Percolation(8);

		p.open(3, 3);
		p.open(4, 4);
		p.open(5, 5);
		p.open(1, 5);
		p.open(2, 5);
		p.open(3, 5);
		p.open(3, 6);
		p.open(3, 8);
		p.open(3, 7);
		p.open(3, 1);
		p.open(3, 2);
		p.open(3, 4);
		p.open(8, 6);
		p.open(7, 6);
		p.open(6, 6);
		p.open(5, 6);
		p.open(5, 4);
		p.open(5, 1);
		p.open(4, 1);
		p.open(8, 8);

		System.out.println(p.isFull(8, 8));

	}

}
