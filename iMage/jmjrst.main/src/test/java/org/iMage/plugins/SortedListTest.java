package org.iMage.plugins;

import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * test to check if all methods in SortedList are working correctly.
 * 
 * @author Joshua Eilebrecht
 *
 */
public class SortedListTest {

	private static SortedList<JmjrstPlugin> list;
	private static SortedListIterator<JmjrstPlugin> iterator;
	private static Iterator<JmjrstPlugin> superIterator;

	/**
	 * sets up all necessary classes before testing.
	 * 
	 * @throws Exception
	 *             any exception that may occur
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		iterator = new SortedListIterator<JmjrstPlugin>();
		superIterator = iterator;
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
		list = new SortedList<JmjrstPlugin>();
	}

	/**
	 * tears down everything necessary after a test.
	 * 
	 * @throws Exception
	 *             any exception that may occur
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * test to check if the constructer is working correctly.
	 */
	@Test
	public void testSortedList() {
		assert (list.getIterator() != null);
	}

	/**
	 * test to check if it can correctly get and convert the iterator.
	 */
	@Test
	public void testIterator() {
		assert (list.iterator().hasNext() == superIterator.hasNext());
		assert (list.iterator().next() == superIterator.next());
	}

	/**
	 * test to check if it can correctly get the iterator.
	 */
	@Test
	public void testGetIterator() {
		assert (list.getIterator().hasNext() == iterator.hasNext());
		assert (list.getIterator().hasPrevious() == iterator.hasPrevious());
		assert (list.getIterator().nextIndex() == iterator.nextIndex());
		assert (list.getIterator().next() == iterator.next());
	}

}
