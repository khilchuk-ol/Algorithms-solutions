import java.util.Arrays;

public class BruteCollinearPoints {
	private LineSegment[] segments;
	private int amount;

	public BruteCollinearPoints(Point[] points) {

		validate(points);
		
		amount = 0;
		segments = new LineSegment[points.length];
		
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				for (int k = j + 1; k < points.length; k++) {
					
					Point p = points[i];
					Point q = points[j];
					Point r = points[k];
					
					if (areColl(p, q, r)) {
						
						for (int t = k + 1; t < points.length; t++) {
							Point s = points[t];
							if (areColl(p, q, s)) {
								Point[] parts = {p, q, r, s};
								Arrays.sort(parts);
								
								segments[amount++] = new LineSegment(parts[0], parts[3]);
							}
						}
					}
				}
			}
		}
	}

	public int numberOfSegments() {
		return amount;
	}

	public LineSegment[] segments() {
		return Arrays.copyOfRange(segments, 0, numberOfSegments());
	}
	
	private void validate(Point[] points) {
        if (points == null) throw new IllegalArgumentException("The input argument is null");
        for (Point point : points) if (point == null) throw new IllegalArgumentException("The input contains null points");

        for (int i = 0; i < points.length; i++)
            for (int j = i+1; j < points.length; j++)
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException("The input contains repeated points");

    }
	
	private boolean areColl(Point p1, Point p2, Point p3) {
        return (Double.compare(p1.slopeTo(p2), p1.slopeTo(p3)) == 0);
    }
}
