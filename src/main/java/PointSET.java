import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
//hi mr young!
public class PointSET {

    private final TreeSet<Point2D> set;
    private static final int CANVAS_SIZE = 640;
    private static final double PEN_RADIUS = 0.01;

    public PointSET() {
        set = new TreeSet<>((a, b) -> {
            if (a.equals(b)) return 0;
            int cmpX = Double.compare(a.x(), b.x());
            return (cmpX != 0) ? cmpX : Double.compare(a.y(), b.y());
        });
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public int size() {
        return set.size();
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Null point");
        set.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Null point");
        return set.contains(p);
    }

    public void draw() {
        StdDraw.setCanvasSize(CANVAS_SIZE, CANVAS_SIZE);
        StdDraw.setPenRadius(PEN_RADIUS);
        for (Point2D p : set) {
            p.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("Null rectangle");
        List<Point2D> inside = new ArrayList<>();
        for (Point2D p : set) {
            if (rect.contains(p)) {
                inside.add(p);
            }
        }
        return inside;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Null point");
        if (set.isEmpty()) return null;

        Point2D nearest = null;
        double minDist = Double.POSITIVE_INFINITY;
        for (Point2D q : set) {
            double dist = p.distanceSquaredTo(q);
            if (minDist > dist) {
                minDist = dist;
                nearest = q;
            }
        }
        return nearest;
    }

    public static void main(String[] args) {
        PointSET ps = new PointSET();
        ps.insert(new Point2D(0.1, 0.2));
        ps.insert(new Point2D(0.3, 0.4));
        ps.insert(new Point2D(0.5, 0.6));
        ps.draw();
        System.out.println("Nearest to (0.2, 0.2): " + ps.nearest(new Point2D(0.2, 0.2)));
    }
}
