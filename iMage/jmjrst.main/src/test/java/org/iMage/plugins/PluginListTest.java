package org.iMage.plugins;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * test to check if all methods in PluginList are working correctly.
 * 
 * @author Joshua Eilebrecht
 *
 */
public class PluginListTest {

	private static PluginList list;

	/**
	 * sets up all necessary classes before testing.
	 * 
	 * @throws Exception
	 *             any exception that may occur
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * tears down everything after testing.
	 * 
	 * @throws Exception
	 *             any exception that may occur
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * sets up everything else before a test.
	 * 
	 * @throws Exception
	 *             any exception that may occur
	 */
	@Before
	public void setUp() throws Exception {
		list = new PluginList();
	}

	/**
	 * tears down anything that needs to be torn down after a test.
	 * 
	 * @throws Exception
	 *             any exception that may occur
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * tests if the constructor is working.
	 */
	@Test
	public void testPluginList() {
		assert (list.getIterable() != null);
	}

	/**
	 * tests if it can correctly get the iterable.
	 */
	@Test
	public void testGetPlugins() {
		assert (list.getPlugins() != null);
	}

	/**
	 * tests if it can correctly get the sorted list.
	 */
	@Test
	public void testGetIterable() {
		assert (list.getIterable() != null);
	}

}
