package org.iMage.plugins;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test to check SortedListCell's methods.
 * 
 * @author Joshua Eilebrecht
 *
 */
public class SortedListCellTest {

	private static TestingPlugin plugin1;
	private static TestingPlugin plugin2;
	private static TestingPlugin plugin3;
	private static SortedListCell<JmjrstPlugin> cell1;
	private static SortedListCell<JmjrstPlugin> cell2;
	private static SortedListCell<JmjrstPlugin> cell3;
	private static SortedListCell<JmjrstPlugin> cell;

	/**
	 * sets up variables that do not change during testing.
	 * 
	 * @throws Exception
	 *             any occurring exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		plugin1 = new TestingPlugin(PluginPriority.LOW, "plugin1");
		plugin2 = new TestingPlugin(PluginPriority.LOW, "plugin2");
		plugin3 = new TestingPlugin(PluginPriority.MID, "plugin3");
		cell1 = new SortedListCell<JmjrstPlugin>(plugin1);
		cell2 = new SortedListCell<JmjrstPlugin>(plugin2);
		cell3 = new SortedListCell<JmjrstPlugin>(plugin3);
		cell1.setNext(cell2);
		cell2.setNext(cell3);
		cell3.setPrevious(cell2);
		cell2.setPrevious(cell1);
	}

	/**
	 * tears down every set variable and object.
	 * 
	 * @throws Exception
	 *             any occurring exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// do nothing
	}

	/**
	 * sets up variables that get changed during testing. Setting up occurs
	 * every time a test is started.
	 * 
	 * @throws Exception
	 *             any occurring exception
	 */
	@Before
	public void setUp() throws Exception {
		cell = new SortedListCell<JmjrstPlugin>(plugin1);
	}

	/**
	 * tears down any variable and object that isn't needed anymore.
	 * 
	 * @throws Exception
	 *             any occurring exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * tests if the constructor works as intended.
	 */
	@Test
	public void testSortedListCell() {
		assert (cell.get().equals(plugin1));
	}

	/**
	 * gets if the getter method works as intended.
	 */
	@Test
	public void testGet() {
		JmjrstPlugin mem1 = cell1.get();
		JmjrstPlugin mem2 = cell2.get();
		JmjrstPlugin mem3 = cell3.get();
		assert (mem1.priority.compareTo(plugin1.priority) == 0);
		assert (mem1.getName().equalsIgnoreCase(plugin1.getName()));
		assert (mem2.priority.compareTo(plugin2.priority) == 0);
		assert (mem2.getName().equalsIgnoreCase(plugin2.getName()));
		assert (mem3.priority.compareTo(plugin3.priority) == 0);
		assert (mem3.getName().equalsIgnoreCase(plugin3.getName()));
	}

	/**
	 * tests if the getter method for the next cell is working as intended.
	 */
	@Test
	public void testGetNext() {
		assert (cell1.getNext().equals(cell2));
		assert (cell2.getNext().equals(cell3));
		assert (cell3.getNext() == null);
	}

	/**
	 * tests if the getter method for the previous cell works as intended.
	 */
	@Test
	public void testGetPrevious() {
		assert (cell1.getPrevious() == null);
		assert (cell2.getPrevious().equals(cell1));
		assert (cell3.getPrevious().equals(cell2));
	}

	/**
	 * tests if the setter method works as intended.
	 */
	@Test
	public void testSet() {
		cell1.set(plugin2);
		assert (cell1.get().equals(plugin2));
	}

	/**
	 * tests if the setter method for the next cell works as intended.
	 */
	@Test
	public void testSetNext() {
		cell.setNext(cell1);
		assert (cell.getNext().equals(cell1));
	}

	/**
	 * tests if the setter method for the previous cell works as intended.
	 */
	@Test
	public void testSetPrevious() {
		cell.setPrevious(cell1);
		assert (cell.getPrevious().equals(cell1));
	}

}
