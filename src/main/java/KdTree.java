import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

public class KdTree {

    private static class Node {
        private final Point2D p;      // the point
        private final RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree

        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
            lb = null;
            rt = null;
        }
    }

    private Node root;       // root of the 2D tree
    private int size;        // size of the tree

    private static final int CANVAS_SIZE = 640;
    private static final double PEN_RADIUS = 0.01;

    // constructs an empty set of points in the unit square
    public KdTree() {
        root = null;
        size = 0;
    }

    // returns if the set is empty
    public boolean isEmpty() {
        return root == null;
    }

    // returns the number of points in the set
    public int size() {
        return size;
    }

    // adds the point to the set
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Null point");
        root = insert(root, p, true, null);
    }

    private Node insert(Node node, Point2D p, boolean isX, RectHV rect) {
        if (node == null) {
            size++;
            Node node1 = new Node(p, rect);
            return node1;
        }

        // compare the point to the current node based on the axis (x or y)
        if (isX) {
            if (p.x() < node.p.x()) {
                double xmin = rect != null ? rect.xmin() : 0;
                double xmax = node.p.x();
                rect = new RectHV(xmin, rect != null ? rect.ymin() : 0, xmax, rect != null ? rect.ymax() : 1);
                node.lb = insert(node.lb, p, !isX, rect);
            } else if (p.x() > node.p.x()) {

                double xmin = node.p.x();
                double xmax = rect != null ? rect.xmax() : 1;
                rect = new RectHV(xmin, rect != null ? rect.ymin() : 0, xmax, rect != null ? rect.ymax() : 1);
                node.rt = insert(node.rt, p, !isX, rect);
            }
        } else {
            if (p.y() < node.p.y()) {
                double ymin = rect != null ? rect.ymin() : 0;
                double ymax = node.p.y();
                rect = new RectHV(rect != null ? rect.xmin() : 0, ymin, rect != null ? rect.xmax() : 1, ymax);
                node.lb = insert(node.lb, p, !isX, rect);
            } else if (p.y() > node.p.y()) {
                double ymin = node.p.y();
                double ymax = rect != null ? rect.ymax() : 1;
                rect = new RectHV(rect != null ? rect.xmin() : 0, ymin, rect != null ? rect.xmax() : 1, ymax);
                node.rt = insert(node.rt, p, !isX, rect);
            }
        }

        return node;
    }

    // returns true if the set contains point p
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Null point");
        return contains(root, p, true); // Start with X-axis comparison
    }

    private boolean contains(Node node, Point2D p, boolean isX) {
        if (node == null) return false;

        if (node.p.equals(p)) return true;

        if (isX) {
            if (p.x() < node.p.x()) {
                return contains(node.lb, p, !isX);
            } else {
                return contains(node.rt, p, !isX);
            }
        } else {
            if (p.y() < node.p.y()) {
                return contains(node.lb, p, !isX);
            } else {
                return contains(node.rt, p, !isX);
            }
        }
    }

    // draws all points to standard draw
    public void draw() {
        StdDraw.setCanvasSize(CANVAS_SIZE, CANVAS_SIZE);
        StdDraw.setPenRadius(PEN_RADIUS);
        draw(root, true); // Start with X-axis comparison
    }

    private void draw(Node node, boolean isX) {
        if (node == null) return;
        node.p.draw();

        // draw line dividing the region
        if (isX) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.p.x(), 0, node.p.x(), 1); // Vertical line
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(0, node.p.y(), 1, node.p.y()); // Horizontal line
        }

        draw(node.lb, !isX);
        draw(node.rt, !isX);
    }

    // returns all the points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("Null rectangle");
        List<Point2D> inside = new ArrayList<>();
        range(root, rect, inside);
        return inside;
    }

    private void range(Node node, RectHV rect, List<Point2D> inside) {
        if (node == null) return;

        if (rect.contains(node.p)) {
            inside.add(node.p);
        }

        if (node.lb != null && rect.intersects(node.lb.rect)) {
            range(node.lb, rect, inside);
        }
        if (node.rt != null && rect.intersects(node.rt.rect)) {
            range(node.rt, rect, inside);
        }
    }

    // returns a nearest neighbor in the set to point p; null if the set is empty.
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Null point");
        if (isEmpty()) return null;
        return nearest(root, p, root.p, Double.POSITIVE_INFINITY);
    }

    private Point2D nearest(Node node, Point2D p, Point2D best, double bestDist) {
        if (node == null) return best;

        double dist = p.distanceSquaredTo(node.p);
        if (dist < bestDist) {
            bestDist = dist;
            best = node.p;
        }

        Node first = (p.x() < node.p.x()) ? node.lb : node.rt;
        Node second = (first == node.lb) ? node.rt : node.lb;

        best = nearest(first, p, best, bestDist);

        if (second != null && (p.x() - node.p.x()) * (p.x() - node.p.x()) < bestDist) {
            best = nearest(second, p, best, bestDist);
        }

        return best;
    }

    // super epic and cool method for testing
    public static void main(String[] args) {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.1, 0.2));
        kdTree.insert(new Point2D(0.3, 0.4));
        kdTree.insert(new Point2D(0.5, 0.6));
        kdTree.insert(new Point2D(0.7, .8));
        kdTree.draw();
        System.out.println("Nearest to (0.2, 0.2): " + kdTree.nearest(new Point2D(0.2, 0.2)));
    }
}
