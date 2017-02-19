package com.igorgurovich;

/**
 * Created by igorgurovich on 2/18/17.
 */


/**
 *  The {@code KdTree} class is a simple implementation of k-d tree that allows
 *   efficient search of the nearest neighbour in two-dimensional euclidean space.
 *
 */
public class KdTree {

    private Node root; // root of tree

    private class   Node {
        private Point point;
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(Point point, int N) {
            this.point = point;
            this.N = N;
        }
    }

    /**
     * Initializes k-d tree
     */
    public KdTree()
    {
    }

    /**
     * Returns true if this tree contains no elements.
     * @return true if this tree contains no elements
     */
    public boolean isEmpty()
    {
        return size() == 0;
    }

    /**
     * Returns number of points in the tree.
     * @return number of points in the tree
     */
    public int size()
    {
        return size(root);

    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /**
     * Adds the point p to the tree (if it is not already in the tree)
     * @param point  point to be inserted
     */
    public void insert(Point point)                   //
    {
        this.root = put(this.root, point, 0);
    }

    private Node put(Node node, Point point, int level) {
        if (node == null) return new Node(point, 1);
        if (node.point.equals(point)) return node;

        boolean is_X_level = level % 2 == 0;

        int cmp = is_X_level ? Point.X_ORDER.compare(point, node.point) :
                Point.Y_ORDER.compare(point, node.point);

        if      (cmp < 0) node.left  = put(node.left,  point, level + 1);
        else  node.right = put(node.right, point, level + 1);

        node.N = 1 + size(node.left) + size(node.right);
        return node;
    }



    /**
     * Returns true if this tree contains the specified element.
     * @param p  element whose presence in this set is to be tested
     * @return true if this tree contains the specified element
     **/
    public boolean contains(Point p)              // does the set contain the point p?
    {
        return contains(root, p, 0);
    }

    private boolean contains(Node node, Point p, int level) {
        if (node == null) return false;
        if (node.point.equals(p)) return true;

        boolean is_X_level = level % 2 == 0;

        int cmp = is_X_level ? Point.X_ORDER.compare(p, node.point) :
                Point.Y_ORDER.compare(p, node.point);

        if (cmp < 0) {
            return (contains(node.left, p, level+1));
        } else {
            return (contains(node.right, p, level+1));
        }
    }

    /**
     * Returns a nearest neighbor of p; null if the tree is empty
     * @param p  element whose nearest neighbour we're looking for
     * @return the nearest neigbour of specified element
     **/
    public Point nearest(Point p)               //
    {
        if (isEmpty()) return null;
        return nearest(root, p, root.point, 0);
    }

    private Point nearest(Node node, Point p, Point nearestPoint, int level) {
        if (node == null) return nearestPoint;

        // recalculate nearestPoint
        if (p.distance(node.point) < p.distance(nearestPoint))
            nearestPoint = node.point;

        boolean isXORDER = level % 2 == 0;

        if (isXORDER) {
            if (p.x() < node.point.x()) {
                // start search in left subtree
                nearestPoint = nearest(node.left, p, nearestPoint, level + 1);
                double nearestDistance = p.distance(nearestPoint);
                // prune right subtree if possible
                if ((node.point.x() - p.x()) < nearestDistance)
                    nearestPoint = nearest(node.right, p, nearestPoint, level + 1);
            } else {
                // start search in right subtree;
                nearestPoint = nearest(node.right, p, nearestPoint, level + 1);
                double nearestDistance = p.distance(nearestPoint);
                // prune left subtree if possible
                if ((p.x() - node.point.x()) < nearestDistance)
                    nearestPoint = nearest(node.left, p, nearestPoint, level + 1);
            }
        } else {
            if (p.y() < node.point.y()) {
                // start search in left subtree
                nearestPoint = nearest(node.left, p, nearestPoint, level + 1);
                double nearestDistance = p.distance(nearestPoint);
                // prune right subtree if possible
                if ((node.point.y() - p.y()) < nearestDistance)
                    nearestPoint = nearest(node.right, p, nearestPoint, level + 1);
            } else {
                // start search in right subtree;
                nearestPoint = nearest(node.right, p, nearestPoint, level + 1);
                double nearestDistance = p.distance(nearestPoint);
                // prune left subtree if possible
                if ((p.y() - node.point.y()) < nearestDistance)
                    nearestPoint = nearest(node.left, p, nearestPoint, level + 1);
            }

        }

        return nearestPoint;
    }

}
