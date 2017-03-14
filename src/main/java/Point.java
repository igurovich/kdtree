import java.util.Comparator;


/**
 *  The {@code Point} class is a data type to represent a
 *  two-dimensional point with real-value euclidean coordinates.
 *
 */
public final class Point {

    /**
     * Compares two points according to their x-coordinate
     */
    public static final Comparator<Point> X_ORDER = new Comparator<Point>() {
        public int compare(Point p1, Point p2) {
            if (p1.x < p2.x) return -1;
            if (p1.x > p2.x) return +1;
            return 0;
        }
    };

    /**
     * Compares two points according to their y-coordinate.
     */
    public static final Comparator<Point> Y_ORDER = new Comparator<Point>() {
        public int compare(Point p1, Point p2) {
            if (p1.y < p2.y) return -1;
            if (p1.y > p2.x) return +1;
            return 0;
        }
    };


    private final double x;    // x coordinate
    private final double y;    // y coordinate

    /**
     * Initializes a new point (x, y).
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @throws IllegalArgumentException if either {@code x} or {@code y}
     *    is {@code Double.NaN}, {@code Double.POSITIVE_INFINITY} or
     *    {@code Double.NEGATIVE_INFINITY}
     */
    public Point(double x, double y) {
        if (Double.isInfinite(x) || Double.isInfinite(y) ||
                Double.isNaN(x) || Double.isNaN(y))
            throw new IllegalArgumentException("Coordinates must be finite and not NaN");
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate.
     * @return the x-coordinate
     */
    public double x() {
        return x;
    }

    /**
     * Returns the y-coordinate.
     * @return the y-coordinate
     */
    public double y() {
        return y;
    }


    /**
     * Returns distance between this point and the other point.
     * @param other the other point
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    /**
     * Returns the square of the distance between this point and other point.
     * @param other the other point
     * @return the square of the distance between this point and the other point
     */
    public double distanceSquared(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return dx*dx + dy*dy;
    }


    /**
     * Compares this point to the specified point.
     *
     * @param  other the other point
     * @return {@code true} if this point equals {@code other};
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Point that = (Point) other;
        return this.x == that.x && this.y == that.y;
    }

    /**
     * Return a string representation of this point.
     * @return a string representation of this point in the format (x, y)
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Returns an integer hash code for this point.
     * @return an integer hash code for this point
     */
    @Override
    public int hashCode() {
        int hashX = ((Double) x).hashCode();
        int hashY = ((Double) y).hashCode();
        return 31*hashX + hashY;
    }
}
