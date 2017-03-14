import java.util.HashSet;
import java.util.Set;

/**
 *  The {@code PointSet} class with brute-force implementation of nearest neighbour method
 *
 */

public class PointSet {
    private Set<Point> set;

    /**
     * Initializes an empty set of points
     */
    public PointSet()
    {
        set = new HashSet<Point>();
    }

    /**
     * Returns true if this set contains no elements.
     * @return true if this set contains no elements
     */
    public boolean isEmpty()
    {
        return set.isEmpty();
    }

    /**
     * Returns number of points in the set.
     * @return number of points in the set
     */
    public int size()
    {
        return set.size();
    }

    /**
     * Adds the point to the set
     * @param point  point to be inserted
     */
    public void insert(Point point)
    {
        set.add(point);
    }

    /**
     * Returns true if this set contains the specified element.
     * @param p  element whose presence in this set is to be tested
     * @return true if this set contains the specified element
     **/
    public boolean contains(Point p)              // does the set contain the point p?
    {
        return set.contains(p);
    }


    /**
     * Returns a nearest neighbor of p; null if the set is empty
     * @param p  element whose nearest neighbour we're looking for
     * @return the nearest neigbour of specified element
     **/
    public Point nearest(Point p)
    {
        Point nearestPoint = null;
        double minDistance = Double.MAX_VALUE;
        for (Point x: set) {
            double dist = p.distance(x);
            if (dist < minDistance) {
                nearestPoint = x;
                minDistance = dist;
            }
        }
        return nearestPoint;
    }
}
