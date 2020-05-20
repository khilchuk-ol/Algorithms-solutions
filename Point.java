import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
	private final Comparator<Point> slopeComp = new SlopeComparator();
	
	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw() {
		StdDraw.point(x, y);
	}

	public void drawTo(Point that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public int compareTo(Point that) {

		int res = this.y - that.y;

		return res != 0 ? res : this.x - that.x;
	}
	
	public Comparator<Point> slopeOrder() {
		return slopeComp;
	}

	public double slopeTo(Point that) {

		if (this.x == that.x) 
            if (this.y == that.y) return Double.NEGATIVE_INFINITY;
            else return Double.POSITIVE_INFINITY;  

        if (this.y == that.y) 
            return 0.0; 

        else return ((double) (that.y - this.y) / (double) (that.x - this.x));
	}

	private class SlopeComparator implements Comparator<Point> {

		@Override
		public int compare(Point o1, Point o2) {
			double slope1 = Point.this.slopeTo(o1);
			double slope2 = Point.this.slopeTo(o2);

			if (slope1 > slope2) return 1;
			if (slope1 < slope2) return -1;

			return 0;
		}

	}
}
