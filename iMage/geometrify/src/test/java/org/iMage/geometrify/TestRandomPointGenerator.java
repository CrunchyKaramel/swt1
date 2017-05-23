package org.iMage.geometrify;

import java.awt.Point;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test to check if RandomPointGenerator can create 1000 valid points.
 * 
 * @author Joshua Eilebrecht
 * @version 1.0
 *
 */
public class TestRandomPointGenerator {
	static RandomPointGenerator generator;

	/**
	 * creates a new generator with a preset size.
	 * 
	 * @throws Exception
	 *             any kind of exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		generator = new RandomPointGenerator(547, 398);
	}

	/**
	 * tears down everything.
	 * 
	 * @throws Exception
	 *             any kind of exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * tests whether RandomPointGenerator can create 1000 valid points.
	 */
	@Test
	public void testNextPoint() {
		for (int a = 0; a < 1000; a++) {
			Point testPoint = generator.nextPoint();
			assert (testPoint.x >= 0);
			assert (testPoint.x < 547);
			assert (testPoint.y >= 0);
			assert (testPoint.y < 398);
		}
	}

}
