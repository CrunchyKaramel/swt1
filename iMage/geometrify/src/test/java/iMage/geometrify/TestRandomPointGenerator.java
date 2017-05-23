package iMage.geometrify;

import java.awt.Point;

import org.iMage.geometrify.RandomPointGenerator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestRandomPointGenerator {
	static RandomPointGenerator generator;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		generator = new RandomPointGenerator(547, 398);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

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
