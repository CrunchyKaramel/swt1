package org.iMage.plugins;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test to check if SortedListIterator works as intended.
 * 
 * @author Joshua Eilebrecht
 *
 */
public class SortedListIteratorTest {

	private static SortedListIterator<JmjrstPlugin> iterator;
	private static TestingPlugin plugin1;
	private static TestingPlugin plugin2;
	private static TestingPlugin plugin3;
	private static TestingPlugin plugin4;
	private static TestingPlugin plugin5;
	private static TestingPlugin plugin6;

	/**
	 * sets up all necessary classes before testing.
	 * 
	 * @throws Exception
	 *             any exception that may occur.
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		plugin1 = new TestingPlugin(PluginPriority.LOW, "plugin1");
		plugin2 = new TestingPlugin(PluginPriority.LOW, "plugin2");
		plugin3 = new TestingPlugin(PluginPriority.MID, "plugin3");
		plugin4 = new TestingPlugin(PluginPriority.MID, "plugin4");
		plugin5 = new TestingPlugin(PluginPriority.HIGH, "plugin5");
		plugin6 = new TestingPlugin(PluginPriority.HIGH, "plugin6");
	}

	/**
	 * tears down everything after testing.
	 * 
	 * @throws Exception
	 *             any exception that may occur.
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * sets up all other classes before a test.
	 * 
	 * @throws Exception
	 *             any exception that may occur
	 */
	@Before
	public void setUp() throws Exception {
		iterator = new SortedListIterator<JmjrstPlugin>();
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
	 * test to check if the constructor is working correctly.
	 */
	@Test
	public void testSortedListIterator() {
		iterator = new SortedListIterator<JmjrstPlugin>();
		assert (iterator.next() == null);
		iterator.previous();
		assert (iterator.previous() == null);
	}

	/**
	 * test to check if the iterator can correctly detect whether there is a
	 * next element or not.
	 */
	@Test
	public void testHasNext() {
		assert (!iterator.hasNext());
		iterator.add(plugin1);
		assert (iterator.hasNext());
		iterator.add(plugin2);
		assert (iterator.hasNext());
		iterator.next();
		assert (iterator.hasNext());
		iterator.next();
		assert (!iterator.hasNext());
		iterator.previous();
		assert (iterator.hasNext());
		iterator.previous();
		assert (iterator.hasNext());
	}

	/**
	 * test to check if it can correctly get the next element.
	 */
	@Test
	public void testNext() {
		iterator.add(plugin1);
		iterator.add(plugin2);
		iterator.add(plugin3);
		assert (iterator.next().equals(plugin2));
		assert (iterator.next().equals(plugin1));
		assert (iterator.next().equals(plugin3));
		assert (iterator.next() == null);
	}

	/**
	 * test to check if the iterator can correctly detect whether there is a
	 * previous element or not.
	 */
	@Test
	public void testHasPrevious() {
		assert (!iterator.hasPrevious());
		iterator.add(plugin1);
		assert (!iterator.hasPrevious());
		iterator.add(plugin2);
		assert (!iterator.hasPrevious());
		iterator.next();
		assert (iterator.hasPrevious());
		iterator.next();
		assert (iterator.hasPrevious());
		iterator.previous();
		assert (iterator.hasPrevious());
		iterator.previous();
		assert (!iterator.hasPrevious());
	}

	/**
	 * test to check if it can correctly get the previous element.
	 */
	@Test
	public void testPrevious() {
		iterator.add(plugin1);
		iterator.add(plugin2);
		iterator.add(plugin3);
		iterator.next();
		iterator.next();
		iterator.next();
		assert (iterator.previous().equals(plugin3));
		assert (iterator.previous().equals(plugin1));
		assert (iterator.previous().equals(plugin2));
		assert (iterator.previous() == null);
	}

	/**
	 * test to check if it can correctly get the index of the next element.
	 */
	@Test
	public void testNextIndex() {
		iterator.add(plugin1);
		iterator.add(plugin2);
		iterator.add(plugin3);
		assert (iterator.nextIndex() == 0);
		iterator.next();
		assert (iterator.nextIndex() == 1);
		iterator.next();
		assert (iterator.nextIndex() == 2);
		iterator.next();
		assert (iterator.nextIndex() == 3);
		iterator.next();
		assert (iterator.nextIndex() == 3);
		iterator.previous();
		assert (iterator.nextIndex() == 2);
		iterator.previous();
		assert (iterator.nextIndex() == 1);
		iterator.previous();
		assert (iterator.nextIndex() == 0);
		iterator.previous();
		assert (iterator.nextIndex() == 0);
	}

	/**
	 * test to check whether it can correctly get the index of the previous
	 * element.
	 */
	@Test
	public void testPreviousIndex() {
		iterator.add(plugin1);
		iterator.add(plugin2);
		iterator.add(plugin3);
		assert (iterator.previousIndex() == 0);
		iterator.next();
		assert (iterator.previousIndex() == 0);
		iterator.next();
		assert (iterator.previousIndex() == 1);
		iterator.next();
		assert (iterator.previousIndex() == 2);
		iterator.next();
		assert (iterator.previousIndex() == 2);
		iterator.previous();
		assert (iterator.previousIndex() == 1);
		iterator.previous();
		assert (iterator.previousIndex() == 0);
		iterator.previous();
		assert (iterator.previousIndex() == 0);
		iterator.previous();
		assert (iterator.previousIndex() == 0);
	}

	/**
	 * test if it can correctly remove elements.
	 */
	@Test
	public void testRemove() {
		iterator.add(plugin1);
		iterator.add(plugin2);
		iterator.add(plugin3);
		assert (iterator.next().equals(plugin2));
		iterator.remove();
		// assert (!iterator.hasPrevious());
		assert (iterator.next().equals(plugin1));
		iterator.remove();
		// assert (!iterator.hasPrevious());
		assert (iterator.next().equals(plugin3));
		iterator.remove();
		// assert (!iterator.hasPrevious());
		// assert (!iterator.hasNext());
	}

	/**
	 * test if this method actually does nothing.
	 */
	@Test
	public void testSet() {
		iterator.add(plugin1);
		iterator.add(plugin2);
		iterator.add(plugin3);
		SortedListIterator<JmjrstPlugin> memIterator = new SortedListIterator<JmjrstPlugin>();
		memIterator.add(plugin1);
		memIterator.add(plugin2);
		memIterator.add(plugin3);
		iterator.set(plugin4);
		assert (!iterator.hasPrevious());
		assert (iterator.next().equals(memIterator.next()));
		assert (iterator.next().equals(memIterator.next()));
		assert (iterator.next().equals(memIterator.next()));
		assert (iterator.next() == memIterator.next());
		iterator.previous();
		iterator.previous();
		memIterator.previous();
		memIterator.previous();
		iterator.set(plugin5);
		assert (iterator.previous().equals(memIterator.previous()));
		iterator.next();
		memIterator.next();
		assert (iterator.next().equals(memIterator.next()));
		assert (iterator.next().equals(memIterator.next()));
		assert (iterator.next() == memIterator.next());
		iterator.previous();
		memIterator.previous();
		assert (iterator.previous().equals(memIterator.previous()));
		assert (iterator.previous() == memIterator.previous());
		iterator.next();
		memIterator.next();
		iterator.next();
		memIterator.next();
	}

	/**
	 * test to check if it can correctly add elements.
	 */
	@Test
	public void testAdd() {
		iterator.add(plugin1);
		iterator.add(plugin2);
		iterator.add(plugin3);
		iterator.add(plugin4);
		iterator.add(plugin5);
		iterator.add(plugin6);
		assert (iterator.next().equals(plugin2));
		assert (iterator.next().equals(plugin1));
		assert (iterator.next().equals(plugin4));
		assert (iterator.next().equals(plugin3));
		assert (iterator.next().equals(plugin6));
		assert (iterator.next().equals(plugin5));
		TestingPlugin[] testPlugins = { plugin1, plugin2, plugin3, plugin4, plugin5, plugin6 };
		int num1 = (int) Math.round(Math.random() * 5);
		int num2;
		do {
			num2 = (int) Math.round(Math.random() * 5);
		} while (num2 == num1);
		int num3;
		do {
			num3 = (int) Math.round(Math.random() * 5);
		} while (num3 == num1 || num3 == num2);
		int num4;
		do {
			num4 = (int) Math.round(Math.random() * 5);
		} while (num4 == num1 || num4 == num2 || num4 == num3);
		int num5;
		do {
			num5 = (int) Math.round(Math.random() * 5);
		} while (num5 == num1 || num5 == num2 || num5 == num3 || num5 == num4);
		int num6;
		do {
			num6 = (int) Math.round(Math.random() * 5);
		} while (num6 == num1 || num6 == num2 || num6 == num3 || num6 == num4);
		iterator.add(testPlugins[num1]);
		iterator.add(testPlugins[num2]);
		iterator.add(testPlugins[num3]);
		iterator.add(testPlugins[num4]);
		iterator.add(testPlugins[num5]);
		iterator.add(testPlugins[num6]);
		while (iterator.next() != null && iterator.hasNext()) {
			iterator.previous();
			PluginPriority pri1 = iterator.next().priority;
			PluginPriority pri2 = iterator.next().priority;
			iterator.previous();
			assert (pri1.equals(PluginPriority.LOW)
					|| pri1.equals(PluginPriority.MID)
							&& (pri2.equals(PluginPriority.MID) || pri2.equals(PluginPriority.HIGH))
					|| pri1.equals(PluginPriority.HIGH) && pri2.equals(PluginPriority.HIGH));
		}
	}

}
