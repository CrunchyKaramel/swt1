package org.iMage.plugins;

import java.util.Iterator;

/**
 * a list required for PluginList.
 * 
 * @author Joshua Eilebrecht
 *
 * @param <T>
 *            any type of object
 */
public class SortedList<T extends JmjrstPlugin> implements Iterable<T> {

	private SortedListIterator<T> iter;

	/**
	 * constructor for SortedList.
	 */
	SortedList() {
		this.iter = new SortedListIterator<T>();
	}

	/**
	 * gives the iterator for this list.
	 */
	@Override
	public Iterator<T> iterator() {
		while (this.iter.hasPrevious()) {
			this.iter.previous();
		}
		return iter;
	}

	/**
	 * returns the iterator in its actual class.
	 * 
	 * @return the iterator as a SortedListIterator
	 */
	SortedListIterator<T> getIterator() {
		return iter;
	}

}
