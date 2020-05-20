
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private static final double CONFIDENCE = 1.96;
	
	private final double[] results;

	private double mean;
	private double stddev;

	// perform independent trials on an n-by-n grid
	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) {
			throw new IllegalArgumentException("Didn't work");
		}

		results = new double[trials];
		int row;
		int col;

		for (int i = 0; i < trials; i++) {
			Percolation p = new Percolation(n);

			while (!p.percolates()) {
				row = StdRandom.uniform(1, n + 1);
				col = StdRandom.uniform(1, n + 1);

				p.open(row, col);
			}

			results[i] = (p.numberOfOpenSites() * 1.0) / (n * n);
		}
	}

	// sample mean of percolation threshold
	public double mean() {
		mean = StdStats.mean(results);

		return mean;
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		stddev = StdStats.stddev(results);

		return stddev;
	}

	// low endpoint of 95% confidence interval
	public double confidenceLo() {
		return mean - (CONFIDENCE * stddev) / Math.sqrt(results.length);
	}

	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		return mean + (CONFIDENCE * stddev) / Math.sqrt(results.length);
	}

	private static int readIntegerFromConsole() {
		int num = StdIn.readInt();

		return num;
	}

	// test client (see below)
	public static void main(String[] args) {

		int n = readIntegerFromConsole();
		int trials = readIntegerFromConsole();

		PercolationStats ps = new PercolationStats(n, trials);

		System.out.println(String.format("%-30s : %f", "mean", ps.mean()));
		System.out.println(String.format("%-30s : %f", "stddev", ps.stddev()));
		System.out.println(
				String.format("%-30s : [%2$f, %3$f]", "95% confidence interval", ps.confidenceLo(), ps.confidenceHi()));
	}
}
