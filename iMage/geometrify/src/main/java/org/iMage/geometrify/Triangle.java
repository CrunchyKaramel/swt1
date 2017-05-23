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

		Point[] points = new Point[3];
		Point[] trianglePoints = { pointA, pointB, pointC };
		for (int a = 0; a < 3; a++) {
			for (int b = 0; b < 3; b++) {
				if (points[b] == null) {
					points[b] = trianglePoints[a];
				} else if (points[b].y < trianglePoints[a].y
						|| (points[b].y == trianglePoints[a].y && points[b].x > trianglePoints[a].x)) {
					Point mem1 = trianglePoints[a];
					Point mem2;
					for (int c = b; c < 3; c++) {
						mem2 = points[c];
						points[c] = mem1;
						mem1 = mem2;
					}
				}
			}
		}
		if (points[0].y == points[1].y) {
			if (!(points[0].y >= p.y)) {
				return false;
			}
		} else if (points[0].x == points[1].x) {
			if (points[2].x > points[0].x) {
				if (!(points[0].x <= p.x)) {
					return false;
				}
			} else {
				if (!(points[0].x >= p.x)) {
					return false;
				}
			}
		} else {
			double incline = (points[0].getY() - points[1].getY()) / (points[0].getX() - points[1].getX());
			if (!(p.getY() <= incline * p.getX() + (points[0].getY() - incline * points[0].getX()))) {
				return false;
			}
		}

		if (points[0].x == points[2].x) {
			if (points[1].x > points[0].x) {
				if (!(points[0].x <= p.x)) {
					return false;
				}
			} else {
				if (!(points[0].x >= p.x)) {
					return false;
				}
			}
		} else {
			double incline = (points[0].getY() - points[2].getY()) / (points[0].getX() - points[2].getX());
			if (!(p.getY() <= incline * p.getX() + (points[0].getY() - incline * points[0].getX()))) {
				return false;
			}
		}

		if (points[1].y == points[2].y) {
			return p.y >= points[1].y;
		} else if (points[1].x == points[2].x) {
			if (points[0].x > points[1].x) {
				return p.x >= points[1].x;
			} else {
				return p.x <= points[1].x;
			}
		} else {
			double incline = (points[1].getY() - points[2].getY()) / (points[1].getX() - points[2].getX());
			return p.y >= incline * p.getX() + (points[1].getY() - incline * points[1].getX());
		}
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
