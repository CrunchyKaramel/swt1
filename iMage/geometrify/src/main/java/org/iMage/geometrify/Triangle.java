package org.iMage.geometrify;

import java.awt.Color;
import java.awt.Point;

/**
 * A triangle.
 *
 * @author Dominic Ziegler, Martin Blersch, Joshua Eilebrecht
 * @version 1.2
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
		if (minX > Math.min(pointB.x, pointC.x)) {
			minX = Math.min(pointB.x, pointC.x);
		}
		if (maxX < Math.max(pointB.x, pointC.x)) {
			maxX = Math.max(pointB.x, pointC.x);
		}
		if (minY > Math.min(pointB.y, pointC.y)) {
			minY = Math.min(pointB.y, pointC.y);
		}
		if (maxY < Math.max(pointB.y, pointC.y)) {
			maxY = Math.max(pointB.y, pointC.y);
		}
		box = new BoundingBox(new Point(minX, maxY), new Point(maxX, minY));
	}

	@Override
	public boolean isInsidePrimitive(Point p) {
		if (pointA.y == pointB.y) {
			if (pointC.y > pointA.y && p.y < pointA.y) {
				return false;
			} else if (p.y > pointA.y) {
				return false;
			}
		} else if (pointA.x == pointB.x) {
			if (pointC.x > pointA.x && p.x < pointA.x) {
				return false;
			} else if (p.x > pointA.x) {
				return false;
			}
		} else {
			double incline = (pointA.getY() - pointB.getY()) / (pointA.getX() - pointB.getX());
			double startY = pointA.getY() - incline * pointA.getX();
			if (pointC.getY() > incline * pointC.getX() + startY && p.getY() < incline * p.getX() + startY) {
				return false;
			} else if (pointC.getY() < incline * pointC.getX() + startY && p.getY() > incline * p.getX() + startY) {
				return false;
			}
		}

		if (pointA.y == pointC.y) {
			if (pointB.y > pointA.y && p.y < pointA.y) {
				return false;
			} else if (p.y > pointA.y) {
				return false;
			}
		} else if (pointA.x == pointC.x) {
			if (pointB.x > pointA.x && p.x < pointA.x) {
				return false;
			} else if (p.x > pointA.x) {
				return false;
			}
		} else {
			double incline = (pointA.getY() - pointC.getY()) / (pointA.getX() - pointC.getX());
			double startY = pointA.getY() - incline * pointA.getX();
			if (pointB.getY() > incline * pointB.getX() + startY && p.getY() < incline * p.getX() + startY) {
				return false;
			} else if (pointB.getY() < incline * pointB.getX() + startY && p.getY() > incline * p.getX() + startY) {
				return false;
			}
		}

		if (pointC.y == pointB.y) {
			if (pointA.y > pointC.y && p.y < pointC.y) {
				return false;
			} else if (p.y > pointC.y) {
				return false;
			}
		} else if (pointC.x == pointB.x) {
			if (pointA.x > pointC.x && p.x < pointC.x) {
				return false;
			} else if (p.x > pointC.x) {
				return false;
			}
		} else {
			double incline = (pointC.getY() - pointB.getY()) / (pointC.getX() - pointB.getX());
			double startY = pointC.getY() - incline * pointC.getX();
			if (pointA.getY() > incline * pointA.getX() + startY && p.getY() < incline * p.getX() + startY) {
				return false;
			} else if (pointA.getY() < incline * pointA.getX() + startY && p.getY() > incline * p.getX() + startY) {
				return false;
			}
		}
		return true;
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
