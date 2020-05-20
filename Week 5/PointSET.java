import java.util.Stack;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSET {
private TreeSet<Point2D> set;
    
    public PointSET() {
        set = new TreeSet<Point2D>();
    }
    
    public boolean isEmpty() {
        return set.isEmpty();
    }
    
    public int size() {
        return set.size();
    }
    
    public void insert(Point2D p) {
        set.add(p);
    }
    
    public boolean contains(Point2D p) {
        return set.contains(p);
    }
    
    public void draw() {
        for (Point2D p:set) p.draw();
    }
    
    public Iterable<Point2D> range(RectHV rect) {
        Stack<Point2D> points = new Stack<Point2D>();
        for (Point2D p:set) {
            if (rect.contains(p)) points.push(p);
        }
        return points;
    }
    
    public Point2D nearest(Point2D p) {
        if (set.isEmpty()) return null;
        Point2D pp = set.first();
        double minDistance = p.distanceSquaredTo(pp);
        for (Point2D point:set) {
            if (p.distanceSquaredTo(point) <= minDistance) {
                pp = point;
                minDistance = p.distanceSquaredTo(point);
            }
        }
        return pp;
    }
    
    public static void main(String[] args) {
    }
}
