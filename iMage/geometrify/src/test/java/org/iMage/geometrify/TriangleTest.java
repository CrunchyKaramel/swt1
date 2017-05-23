package org.iMage.geometrify;

import java.awt.Color;
import java.awt.Point;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests Triangle for proper functionality.
 * 
 * @author Joshua Eilebrecht
 * @version 1.0
 *
 */
public class TriangleTest {
	static Triangle testTriangle;
	static Color testColor;

	/**
	 * Creates a triangle and a color for the triangle for testing purposes.
	 * 
	 * @throws Exception
	 *             any type of exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testTriangle = new Triangle(new Point(12, 5), new Point(6, 77), new Point(12, 12));
		float r = 14.66f;
		float g = 3.111f;
		float b = 9.7f;
		testColor = new Color(r, g, b);

	}

	/**
	 * Tears down the test.
	 * 
	 * @throws Exception
	 *             any type of exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Tests whether isInsidePrimitive() detects a point outside the triangle, a
	 * point of the triangle, a point on the edge of it and a point inside it.
	 */
	@Test
	public void testIsInsidePrimitive() {
		assert (!testTriangle.isInsidePrimitive(new Point(4, 4)));
		assert (testTriangle.isInsidePrimitive(new Point(6, 77)));
		assert (testTriangle.isInsidePrimitive(new Point(8, 53)));
		assert (testTriangle.isInsidePrimitive(new Point(9, 42)));
	}

	/**
	 * Test whether the bounding box of the tested triangle has the right size.
	 */
	@Test
	public void testGetBoundingBox() {
		int[] upperLeft = { testTriangle.getBoundingBox().getUpperLeftCorner().x,
				testTriangle.getBoundingBox().getUpperLeftCorner().y };
		int[] lowerRight = { testTriangle.getBoundingBox().getLowerRightCorner().x,
				testTriangle.getBoundingBox().getLowerRightCorner().y };
		assert (upperLeft[0] == 6);
		assert (upperLeft[1] == 77);
		assert (lowerRight[0] == 12);
		assert (lowerRight[1] == 5);
	}

	/**
	 * tests whether the tested triangle returns the right color or not.
	 */
	@Test
	public void testGetColor() {
		assert (testTriangle.getColor().equals(testColor));
	}

}
