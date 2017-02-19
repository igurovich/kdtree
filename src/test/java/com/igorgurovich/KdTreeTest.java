package com.igorgurovich;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;


/**
 * Unit test for KdTree
 */
public class KdTreeTest
{

    private Random random = new Random();


    @Test
    public void testPerformance() {

        KdTree tree = new KdTree();
        PointSet set = new PointSet();
        loadRandomPoints(50000, tree, set);

        ArrayList<Point> points = new ArrayList<Point>();
        for (int i = 0; i < 1000; i++) {
            points.add(generateRandomPoint());
        }

        long startTime = System.nanoTime();
        for (Point p: points) {
            Point p1 = tree.nearest(p);
        }
        long elapsedTime = System.nanoTime() - startTime;

        System.out.println("Searching in KdTree: elapsed time is "
                + elapsedTime/1000000 + " milliseconds.");

        startTime = System.nanoTime();
        for (Point p: points) {
            Point p1 = set.nearest(p);
        }
        elapsedTime = System.nanoTime() - startTime;

        System.out.println("Searching in PointSet: elapsed time is "
                + elapsedTime/1000000 + " milliseconds.");
    }

    @Test
    public void testRandomSet() {
        KdTree tree = new KdTree();
        PointSet set = new PointSet();
        loadRandomPoints(50000, tree, set);

        for (int i = 0; i < 1000; i++) {
            Point p = generateRandomPoint();
            Point p1 = tree.nearest(p);
            Point p2 = set.nearest(p);
            assertEquals(p1, p2);
        }
    }

    @Test
    public void testRandomVerticalSet() {
        KdTree tree = new KdTree();
        PointSet set = new PointSet();
        loadRandomVerticalPoints(50000, 0.7, tree, set);

        for (int i = 0; i < 1000; i++) {
            Point p = generateRandomPoint();
            Point p1 = tree.nearest(p);
            Point p2 = set.nearest(p);
            assertEquals(p1, p2);
        }
    }

    @Test
    public void testRandomHorizontalSet() {
        KdTree tree = new KdTree();
        PointSet set = new PointSet();
        loadRandomHorizontalPoints(50000, 0.3, tree, set);

        for (int i = 0; i < 1000; i++) {
            Point p = generateRandomPoint();
            Point p1 = tree.nearest(p);
            Point p2 = set.nearest(p);
            assertEquals(p1, p2);
        }
    }


    private void loadPoints(String filename, KdTree tree, PointSet set) {
        File file = new File(filename);

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                try {
                    double x = sc.nextDouble();
                    double y = sc.nextDouble();
                    Point p = new Point(x, y);
                    tree.insert(p);
                    set.insert(p);
                }
                catch (java.util.NoSuchElementException ex) {

                }
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
         ;
    }

    private void loadRandomPoints(int numPoints, KdTree tree, PointSet set) {
        for (int i=0; i < numPoints; i++) {
            Point point = generateRandomPoint();
            tree.insert(point);
            set.insert(point);
        }
    }

    private void loadRandomHorizontalPoints(int numPoints, double y, KdTree tree, PointSet set) {
        for (int i=0; i < numPoints; i++) {
            Point point = generateRandomHorizontalPoint(y);
            tree.insert(point);
            set.insert(point);
        }
    }

    private void loadRandomVerticalPoints(int numPoints, double x, KdTree tree, PointSet set) {
        for (int i=0; i < numPoints; i++) {
            Point point = generateRandomVerticalPoint(x);
            tree.insert(point);
            set.insert(point);
        }
    }


    private Point generateRandomPoint() {
        return new Point(random.nextDouble(), random.nextDouble());
    }

    private Point generateRandomHorizontalPoint(double y) {
        return new Point(random.nextDouble(), y);
    }

    private Point generateRandomVerticalPoint(double x) {
        return new Point(x, random.nextDouble());
    }

}
