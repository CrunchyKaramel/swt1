package org.iMage.geometrify;

import java.awt.Color;
import java.awt.Point;

/**
 * A triangle.
 *
 * @author Dominic Ziegler, Martin Blersch, Joshua Eilebrecht
 * @version 1.1
 */
public class Triangle implements IPrimitive {

	/*
	 * All 3 points to define the outline of the triangle.
	 */
	Point pointA;
	Point pointB;
	Point pointC;

	/*
	 * color of the triangle.
	 */
	Color color;

	/*
	 * the bounding box of the triangle
	 */
	BoundingBox box;

	/**
	 * Creates a new triangle from the given vertices.
	 *
	 * @param a
	 *            the first vertex
	 * @param b
	 *            the second vertex
	 * @param c
	 *            the third vertex
	 */
	public Triangle(Point a, Point b, Point c) {
		pointA = a;
		pointB = b;
		pointC = c;
		int minX = pointA.x;
		int maxX = pointA.x;
		int minY = pointA.y;
		int maxY = pointA.y;
		if (minX < Math.min(pointB.x, pointC.x)) {
			minX = Math.min(pointB.x, pointC.x);
		}
		if (maxX < Math.max(pointB.x, pointC.x)) {
			maxX = Math.max(pointB.x, pointC.x);
		}
		if (minY < Math.min(pointB.y, pointC.y)) {
			minY = Math.min(pointB.y, pointC.y);
		}
		if (maxY < Math.max(pointB.y, pointC.y)) {
			maxY = Math.max(pointB.y, pointC.y);
		}
		box = new BoundingBox(new Point(minX, maxY), new Point(maxX, minY));
	}

	@Override
	public boolean isInsidePrimitive(Point p) {

		// functions for the outline of the triangle
		double[] aB = new double[2];
		double[] aC = new double[2];
		double[] bC = new double[2];
		aB[0] = (pointB.getY() - pointA.getY()) / (pointB.getX() - pointA.getX());
		aB[1] = pointA.getY() - aB[0] * pointA.getX();
		aC[0] = (pointC.getY() - pointA.getY()) / (pointC.getX() - pointA.getX());
		aC[1] = pointA.getY() - aB[0] * pointA.getX();
		bC[0] = (pointC.getY() - pointB.getY()) / (pointC.getX() - pointB.getX());
		bC[1] = pointB.getY() - aB[0] * pointB.getX();

		// determining whether the point is inside the triangle.
		if (pointA.getY() >= pointB.getY() && pointA.getY() >= pointC.getY()) {
			if (p.getX() <= (aB[0] * p.getX() + aB[1]) && p.getX() <= (aC[0] * p.getX() + aC[1])
					&& p.getX() >= (bC[0] * p.getX() + bC[1])) {
				return true;
			}
		}
		if (pointB.getY() >= pointA.getY() && pointB.getY() >= pointC.getY()) {
			if (p.getX() <= (aB[0] * p.getX() + aB[1]) && p.getX() <= (bC[0] * p.getX() + bC[1])
					&& p.getX() >= (aC[0] * p.getX() + aC[1])) {
				return true;
			}
		}
		if (pointC.getY() >= pointA.getY() && pointA.getY() >= pointB.getY()) {
			if (p.getX() <= (bC[0] * p.getX() + bC[1]) && p.getX() <= (aC[0] * p.getX() + aC[1])
					&& p.getX() >= (aB[0] * p.getX() + aB[1])) {
				return true;
			}
		}
		return false;
	}

	@Override
	public BoundingBox getBoundingBox() {
		return box;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color c) {
		color = c;
	}

}
