/**
 * Description: Represents a set of points in the unit square
 * (all points have x- and y-coordinates between 0 and 1)
 * using <code>algs4.Point2D</code> to represent a point,
 * <code>algs4.RectHV</code> to represent a rectangle,
 * a red-black BST (used in <code>algs4.SET</code> or <code>java.util.TreeSet</code>)
 * to support range search (find all the points contained in a query rectangle)
 * and nearest-neighbor search (find a closest point to a query point).
 * <p>
 * This is the brute-force implementation using a Red-Black BST.
 */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.SET;

import java.util.ArrayList;
import java.util.TreeSet;

public class PointSET {

    /**
     * An integer for the size of the canvas.
     */
    private static final int CANVAS_SIZE = 640;

    /**
     * A double for the size of the pen radius.
     */
    private static final double PEN_RADIUS = 0.01;

    /**
     * Constructs an empty set of points in the unit square.
     */
    public PointSET() {
        // TODO: your code here
    }

    /**
     * Returns true if the set is empty.
     * @return whether the set is empty
     */
    public boolean isEmpty() {
        // TODO: your code here
        return false;
    }

    /**
     * Returns the number of points in the set.
     * @return the number of points in the set
     */
    public int size() {
        // TODO: your code here
        return 0;
    }

    /**
     * Adds the point to the set (if not already in the set).
     * @param p the point to be inserted
     * @throws IllegalArgumentException if p is null
     */
    public void insert(Point2D p) {
        // TODO: your code here
    }

    /**
     * Returns true if the set contains point p.
     * @param p the point to be checked for
     * @return true if the set contains point p
     * @throws IllegalArgumentException if p is null
     */
    public boolean contains(Point2D p) {
        // TODO: your code here
        return false;
    }

    /**
     * Draws all points to standard draw.
     */
    public void draw() {
        // TODO: your code here - feel free to modify what is here
        StdDraw.setCanvasSize(CANVAS_SIZE, CANVAS_SIZE);
        StdDraw.setPenRadius(PEN_RADIUS);
    }

    /**
     * Returns all the points that are inside the rectangle.
     * @param rect the rectangle
     * @return all the points that are inside the rectangle
     * @throws IllegalArgumentException if rect is null
     */
    public Iterable<Point2D> range(RectHV rect) {
        // TODO: your code here
        return new ArrayList<>();
    }

    /**
     * Returns a nearest neighbor in the set to point p; null if the set is empty.
     * @param p the point to be checked
     * @return a nearest neighbor in the set to point p; null if the set is empty
     * @throws IllegalArgumentException if p is null
     */
    public Point2D nearest(Point2D p) {
        // TODO: your code here
        return new Point2D(0.0, 0.0);
    }

    /**
     * Optional method for your testing.
     * @param args the arguments
     */
    public static void main(String[] args) {
    }
}
