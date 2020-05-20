import java.util.Arrays;
import java.util.Stack;

public class FastCollinearPoints {
	private int amount;
    private LineSegment[] collSegm;
    private Segment[] collSegmGeneral;

    public FastCollinearPoints(Point[] points) {
        validateInput(points);
        amount = 0;
        collSegm = new LineSegment[points.length];
        collSegmGeneral = new Segment[points.length];

        for (Point p : points) {
            Point[] slopesToP = getSlopesTo(p, points);

            for (int j = 0; j < slopesToP.length - 2; j++) {
                Point q = slopesToP[j];
                Point[] candidatePoints = Arrays.copyOfRange(slopesToP, j+1, slopesToP.length);
                Point[] collinearSequence = findCollinearSequence(p, q, candidatePoints);

                if (collinearSequence.length >= 4) {
                    addSegment(collinearSequence);
                    break;
                }
            }
        }
    }

    public int numberOfSegments() {
        return amount;
    }

    public LineSegment[] segments() {
        return Arrays.copyOfRange(collSegm, 0, numberOfSegments());
    }

    private boolean isIncluded(Segment s) {
        for (int i = 0; i < amount; i++)
            if (s.equals(collSegmGeneral[i]))
                return true;

        return false;
    }

    private Point[] findCollinearSequence(Point p, Point q, Point[] candidatePoints) {
        Stack<Point> collinearPoints = new Stack<Point>();
        collinearPoints.push(p);
        collinearPoints.push(q);

        for (Point r : candidatePoints)
            if (areCollinear(p, collinearPoints.peek(), r))
                collinearPoints.push(r);
            else
                break;

        return collinearPoints.toArray(new Point[0]);
    }

    private void addSegment(Point[] ps) {
        Arrays.sort(ps);
        Segment s = new Segment(ps[0], ps[ps.length - 1]);

        if (!isIncluded(s)) {
            collSegmGeneral[amount] = s;
            collSegm[amount] = new LineSegment(s.minPoint, s.maxPoint);
            amount++;
        }
    }

    private void validateInput(Point[] points) {
        if (points == null) throw new IllegalArgumentException("The input cannot be null");

        for (Point point : points) if (point == null) throw new IllegalArgumentException("The input cannot contain null points");

        for (int i = 0; i < points.length; i++)
            for (int j = i + 1; j < points.length; j++)
                if (points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException("The input cannot contain repeated points");
    }

    private boolean areCollinear(Point p1, Point p2, Point p3) {
        return (Double.compare(p1.slopeTo(p2), p1.slopeTo(p3)) == 0);
    }

    private Point[] getSlopesTo(Point p, Point[] points) {
        Point[] slopesToP = Arrays.copyOf(points, points.length);
        Arrays.sort(slopesToP, p.slopeOrder());

        return Arrays.copyOfRange(slopesToP, 1, slopesToP.length);
    }

    private class Segment {
        public Point minPoint, maxPoint;

        public Segment(Point minPoint, Point maxPoint) {
            this.minPoint = minPoint;
            this.maxPoint = maxPoint;
        }

        public boolean equals(Segment other) {
            return ((minPoint.compareTo(other.minPoint) == 0) && (maxPoint.compareTo(other.maxPoint) == 0));
        }
    }
}
